package com.tortas.decoradas.pago.aplicacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;
import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.pago.dominio.modelo.PagoRealizado;
import com.tortas.decoradas.pago.dominio.modelo.ResultadoConfirmacionPago;
import com.tortas.decoradas.pago.dominio.puerto.in.CasoDeUsoConfirmarPago;
import com.tortas.decoradas.pago.dominio.puerto.out.PuertoPasarelaPago;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CasoDeUsoConfirmarPagoImpl implements CasoDeUsoConfirmarPago {

    private static final Logger log = LoggerFactory.getLogger(CasoDeUsoConfirmarPagoImpl.class);

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoRepositorioProducto puertoRepositorioProducto;
    private final PuertoPasarelaPago puertoPasarelaPago;
    private final PuertoNotificador puertoNotificador;

    public CasoDeUsoConfirmarPagoImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoRepositorioProducto puertoRepositorioProducto,
            PuertoPasarelaPago puertoPasarelaPago, PuertoNotificador puertoNotificador) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoRepositorioProducto = puertoRepositorioProducto;
        this.puertoPasarelaPago = puertoPasarelaPago;
        this.puertoNotificador = puertoNotificador;
    }

    @Override
    public synchronized ResultadoConfirmacionPago confirmarDesdeRetorno(String emailUsuario, Long idPedido) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        Pedido pedido = puertoRepositorioPedido.BuscarPorId(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }
        if (!pedido.getId_usuario().equals(usuario.getId())) {
            throw new IllegalArgumentException("No tenés permiso sobre este pedido.");
        }
        if (pedido.getEstado() != Estado.INICIADO) {
            return resultado(pedido.getEstado(), pedido.getEstado() == Estado.CUMPLIDO ? "APROBADO" : "SIN_PAGO");
        }

        return evaluar(pedido, puertoPasarelaPago.buscarPagosDePedido(idPedido));
    }

    @Override
    public synchronized void confirmarDesdeWebhook(String idPago) {
        Optional<PagoRealizado> pago = puertoPasarelaPago.obtenerPago(idPago);
        if (pago.isEmpty() || pago.get().idPedido() == null) {
            return;
        }
        Pedido pedido = puertoRepositorioPedido.BuscarPorId(pago.get().idPedido());
        if (pedido == null) {
            return;
        }
        if (pedido.getEstado() == Estado.CANCELADO && "approved".equals(pago.get().estado())) {
            log.warn("Pago aprobado {} de un pedido CANCELADO ({}): hay que reembolsarlo a mano.",
                    pago.get().idPago(), pedido.getIdpedido());
            return;
        }
        if (pedido.getEstado() != Estado.INICIADO) {
            return;
        }
        evaluar(pedido, List.of(pago.get()));
    }

    private ResultadoConfirmacionPago evaluar(Pedido pedido, List<PagoRealizado> pagos) {
        long envio = TotalDelPedido.envio(pedido, puertoRepositorioProducto.obtenerListaDeCodigosPostales());
        long total = TotalDelPedido.productos(pedido) + envio;

        boolean aprobado = pagos.stream()
                .anyMatch(p -> "approved".equals(p.estado()) && p.monto() + 0.01 >= total);
        if (aprobado) {
            puertoRepositorioPedido.GuardarCostoEnvio(pedido.getIdpedido(), envio);
            Pedido actualizado = puertoRepositorioPedido.ActualizarEstado(pedido.getIdpedido(), Estado.CUMPLIDO);
            avisarPagoConfirmado(pedido);
            return resultado(actualizado.getEstado(), "APROBADO");
        }

        String estadoPago = "SIN_PAGO";
        if (!pagos.isEmpty()) {
            String ultimo = pagos.get(0).estado();
            estadoPago = switch (ultimo == null ? "" : ultimo) {
                case "pending", "in_process", "in_mediation", "authorized" -> "PENDIENTE";
                case "rejected", "cancelled", "refunded", "charged_back" -> "RECHAZADO";
                default -> "PENDIENTE";
            };
        }
        return resultado(pedido.getEstado(), estadoPago);
    }

    private void avisarPagoConfirmado(Pedido pedido) {
        Usuario cliente = null;
        try {
            cliente = puertoRepositorioUsuario.buscarPorId(pedido.getId_usuario()).orElse(null);
            if (cliente != null) {
                puertoNotificador.notificar(cliente.getEmail(), cliente.getNombre(),
                        TipoNotificacion.PAGO_CONFIRMADO, Map.of("idPedido", String.valueOf(pedido.getIdpedido())));
            }
        } catch (RuntimeException e) {
        }

        if (cliente == null) {
            return;
        }
        try {
            Map<String, String> datosAdmin = Map.of(
                    "idPedido", String.valueOf(pedido.getIdpedido()),
                    "clienteNombre", cliente.getNombre(),
                    "clienteEmail", cliente.getEmail(),
                    "fechaEntrega", pedido.getFecha_de_pedido().toString());
            for (Usuario admin : puertoRepositorioUsuario.buscarAdmins()) {
                puertoNotificador.notificar(admin.getEmail(), admin.getNombre(),
                        TipoNotificacion.NUEVO_PEDIDO_ADMIN, datosAdmin);
            }
        } catch (RuntimeException e) {
            log.warn("No se pudo avisar a los admins del pedido {}", pedido.getIdpedido(), e);
        }
    }

    private ResultadoConfirmacionPago resultado(Estado estado, String estadoPago) {
        return new ResultadoConfirmacionPago(estado.name(), estadoPago);
    }
}
