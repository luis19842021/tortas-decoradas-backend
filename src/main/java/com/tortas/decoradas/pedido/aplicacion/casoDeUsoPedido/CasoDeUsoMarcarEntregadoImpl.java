package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import com.tortas.decoradas.pedido.dominio.modelo.EstadoPreparacion;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoMarcarEntregado;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoMarcarEntregadoImpl implements CasoDeUsoMarcarEntregado {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoMarcarEntregadoImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public Pedido marcarEntregado(Long idPedido) {
        Pedido pedido = puertoRepositorioPedido.BuscarPorId(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }

        if (pedido.getEstadoPreparacion() != EstadoPreparacion.COCINANDO) {
            throw new IllegalArgumentException(
                    "El pedido tiene que estar COCINANDO para poder marcarlo como entregado (está "
                            + pedido.getEstadoPreparacion() + ").");
        }

        return puertoRepositorioPedido.ActualizarEstadoPreparacion(idPedido, EstadoPreparacion.ENTREGADO);
    }
}
