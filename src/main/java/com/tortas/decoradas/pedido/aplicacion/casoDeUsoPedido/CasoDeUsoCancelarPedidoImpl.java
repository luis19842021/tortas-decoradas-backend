package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;
import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.EstadoPreparacion;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCancelarPedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoCancelarPedidoImpl implements CasoDeUsoCancelarPedido {

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoNotificador puertoNotificador;
    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoCancelarPedidoImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoNotificador puertoNotificador,
            PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoNotificador = puertoNotificador;
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Pedido cancelar(String emailUsuario, Long idPedido) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        Pedido pedido = puertoRepositorioPedido.BuscarPorId(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }

        if (!pedido.getId_usuario().equals(usuario.getId())) {
            throw new IllegalArgumentException("No tenés permiso sobre este pedido.");
        }

        if (pedido.getEstado() != Estado.CUMPLIDO) {
            throw new IllegalArgumentException("Este pedido no se puede cancelar desde acá.");
        }

        if (pedido.getEstadoPreparacion() == EstadoPreparacion.ENTREGADO) {
            throw new IllegalArgumentException("Un pedido ya entregado no se puede cancelar.");
        }

        int porcentajeDevuelto = calcularPorcentajeDevuelto(pedido.getFecha_de_pedido());
        long envio = pedido.getCostoEnvio() != null
                ? pedido.getCostoEnvio()
                : CostoDeEnvio.de(pedido, puertoRepositorioProducto.obtenerListaDeCodigosPostales());
        long total = calcularTotal(pedido) + envio;
        long montoADevolver = total * porcentajeDevuelto / 100;

        Pedido cancelado = puertoRepositorioPedido.CancelarPedido(idPedido, montoADevolver, porcentajeDevuelto);
        cancelado.setCostoEnvio(envio);

        Map<String, String> datosCliente = Map.of(
                "idPedido", String.valueOf(idPedido),
                "montoADevolver", String.valueOf(montoADevolver),
                "porcentajeDevuelto", String.valueOf(porcentajeDevuelto));
        puertoNotificador.notificar(usuario.getEmail(), usuario.getNombre(), TipoNotificacion.PEDIDO_CANCELADO_CLIENTE,
                datosCliente);

        Map<String, String> datosAdmin = Map.of(
                "idPedido", String.valueOf(idPedido),
                "clienteNombre", usuario.getNombre(),
                "clienteEmail", usuario.getEmail(),
                "montoADevolver", String.valueOf(montoADevolver),
                "porcentajeDevuelto", String.valueOf(porcentajeDevuelto));
        for (Usuario admin : puertoRepositorioUsuario.buscarAdmins()) {
            puertoNotificador.notificar(admin.getEmail(), admin.getNombre(), TipoNotificacion.PEDIDO_CANCELADO_ADMIN,
                    datosAdmin);
        }

        return cancelado;
    }

    private int calcularPorcentajeDevuelto(LocalDate fechaDeEntrega) {
        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), fechaDeEntrega);

        if (diasRestantes < 2) {
            return 0;
        }
        if (diasRestantes < 7) {
            return 50;
        }
        return 90;
    }

    private long calcularTotal(Pedido pedido) {
        return pedido.getItems().stream()
                .mapToLong(item -> item.getPrecioUnitario() * item.getCantidad())
                .sum();
    }
}
