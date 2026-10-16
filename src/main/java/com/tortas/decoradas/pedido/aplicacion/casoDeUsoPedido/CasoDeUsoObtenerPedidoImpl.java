package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidos;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoObtenerPedidoImpl implements CasoDeUsoObtenerPedidos {

    private final PuertoRepositorioPedido puertoRepositorioPedido ;

    public CasoDeUsoObtenerPedidoImpl(PuertoRepositorioPedido puertoRepositorioPedido){
        this.puertoRepositorioPedido=puertoRepositorioPedido;
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
       return puertoRepositorioPedido.BuscarTodosLosPedido();
    }

}
