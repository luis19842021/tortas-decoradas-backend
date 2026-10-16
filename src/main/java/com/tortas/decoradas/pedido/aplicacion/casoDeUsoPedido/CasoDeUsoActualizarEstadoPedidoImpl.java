package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.util.Map;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;
import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoActualizarEstadoPedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoActualizarEstadoPedidoImpl implements CasoDeUsoActualizarEstadoPedido {

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoNotificador puertoNotificador;

    public CasoDeUsoActualizarEstadoPedidoImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoNotificador puertoNotificador) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoNotificador = puertoNotificador;
    }

    @Override
    public Pedido actualizarEstado(String emailUsuario, Long idPedido, Estado nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("Falta el estado nuevo.");
        }

        if (nuevoEstado == Estado.CUMPLIDO) {
            throw new IllegalArgumentException("El pago se confirma solo a través de Mercado Pago.");
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

        if (pedido.getEstado() != Estado.INICIADO) {
            throw new IllegalArgumentException("El pedido ya está " + pedido.getEstado() + ".");
        }

        Pedido actualizado = puertoRepositorioPedido.ActualizarEstado(idPedido, nuevoEstado);

        TipoNotificacion tipo = nuevoEstado == Estado.CUMPLIDO ? TipoNotificacion.PAGO_CONFIRMADO
                : nuevoEstado == Estado.CANCELADO ? TipoNotificacion.PAGO_CANCELADO : null;
        if (tipo != null) {
            puertoNotificador.notificar(usuario.getEmail(), usuario.getNombre(), tipo,
                    Map.of("idPedido", String.valueOf(idPedido)));
        }

        return actualizado;
    }
}
