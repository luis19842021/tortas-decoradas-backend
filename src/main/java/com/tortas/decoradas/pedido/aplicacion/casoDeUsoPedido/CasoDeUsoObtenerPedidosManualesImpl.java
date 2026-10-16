package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidosManuales;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoObtenerPedidosManualesImpl implements CasoDeUsoObtenerPedidosManuales {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoObtenerPedidosManualesImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public List<PedidoManual> obtenerPedidosManuales() {
        return puertoRepositorioPedido.ObtenerPedidosManuales();
    }
}
