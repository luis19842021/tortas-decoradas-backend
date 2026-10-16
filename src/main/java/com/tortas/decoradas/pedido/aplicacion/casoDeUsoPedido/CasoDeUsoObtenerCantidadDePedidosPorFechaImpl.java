package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.util.List;
import java.util.Map;

import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerCantidadDePedidosPorFechas;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoObtenerCantidadDePedidosPorFechaImpl implements CasoDeUsoObtenerCantidadDePedidosPorFechas {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoObtenerCantidadDePedidosPorFechaImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public List<Map<String,Object>> obtenerCantidadDePedidosPorFechas() {
        return puertoRepositorioPedido.BuscarCantidadDePedidosPorFechas();
    }

}
