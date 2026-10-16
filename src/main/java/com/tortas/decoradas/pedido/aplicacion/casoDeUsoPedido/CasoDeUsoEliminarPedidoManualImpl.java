package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoEliminarPedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoEliminarPedidoManualImpl implements CasoDeUsoEliminarPedidoManual {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoEliminarPedidoManualImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public void eliminarPedidoManual(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Falta el id del pedido manual.");
        }
        if (!puertoRepositorioPedido.ExistePedidoManual(id)) {
            throw new IllegalArgumentException("Ese pedido manual no existe.");
        }
        puertoRepositorioPedido.EliminarPedidoManual(id);
    }
}
