package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDate;
import java.util.Map;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;
import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.EstadoPreparacion;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.modelo.ReglasDeAgenda;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoReprogramarPedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoReprogramarPedidoImpl implements CasoDeUsoReprogramarPedido {

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoNotificador puertoNotificador;

    public CasoDeUsoReprogramarPedidoImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoNotificador puertoNotificador) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoNotificador = puertoNotificador;
    }

    @Override
    public Pedido reprogramar(String emailUsuario, Long idPedido, LocalDate nuevaFecha) {
        if (nuevaFecha == null) {
            throw new IllegalArgumentException("Falta la nueva fecha de entrega.");
        }
        if (!ReglasDeAgenda.respetaAnticipacionMinima(nuevaFecha, LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La nueva fecha tiene que tener, como mínimo, "
                            + ReglasDeAgenda.DIAS_MINIMOS_DE_ANTICIPACION + " días de anticipación.");
        }

        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        Pedido pedido = puertoRepositorioPedido.BuscarPorId(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }

        if (!pedido.getId_usuario().equals(usuario.getId())) {
            throw new IllegalArgumentException("No tenés permiso sobre este pedido.");
        }

        if (pedido.getEstado() == Estado.CANCELADO) {
            throw new IllegalArgumentException("Un pedido cancelado no se puede reprogramar.");
        }

        if (pedido.getEstadoPreparacion() != null && pedido.getEstadoPreparacion() != EstadoPreparacion.DISENANDO) {
            throw new IllegalArgumentException(
                    "Ya no se puede reprogramar: el pedido está " + pedido.getEstadoPreparacion() + ".");
        }

        if (pedido.isReprogramado()) {
            throw new IllegalArgumentException("Este pedido ya se reprogramó una vez. No se puede volver a cambiar la fecha.");
        }

        if (nuevaFecha.equals(pedido.getFecha_de_pedido())) {
            throw new IllegalArgumentException("Esa ya es la fecha actual del pedido.");
        }

        if (puertoRepositorioPedido.EstaDiaBloqueado(nuevaFecha)) {
            throw new IllegalArgumentException("Esa fecha no está disponible.");
        }

        ConfiguracionAgenda configuracionAgenda = puertoRepositorioPedido.ObtenerConfiguracionAgenda();
        long pedidosEnNuevaFecha = puertoRepositorioPedido.ContarPedidosEnFecha(nuevaFecha);
        if (pedidosEnNuevaFecha >= configuracionAgenda.getCupoMaximoPorDia()) {
            throw new IllegalArgumentException("Esa fecha ya no tiene cupo disponible.");
        }

        Pedido reprogramado = puertoRepositorioPedido.ReprogramarPedido(idPedido, nuevaFecha);

        puertoNotificador.notificar(usuario.getEmail(), usuario.getNombre(), TipoNotificacion.PEDIDO_REPROGRAMADO,
                Map.of("idPedido", String.valueOf(idPedido), "nuevaFecha", nuevaFecha.toString()));

        Map<String, String> datosAdmin = Map.of(
                "idPedido", String.valueOf(idPedido),
                "clienteNombre", usuario.getNombre(),
                "clienteEmail", usuario.getEmail(),
                "nuevaFecha", nuevaFecha.toString());
        for (Usuario admin : puertoRepositorioUsuario.buscarAdmins()) {
            puertoNotificador.notificar(admin.getEmail(), admin.getNombre(), TipoNotificacion.PEDIDO_REPROGRAMADO_ADMIN,
                    datosAdmin);
        }

        return reprogramado;
    }
}
