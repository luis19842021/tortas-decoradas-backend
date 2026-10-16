package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;

public interface CasoDeUsoObtenerPedidosManuales {
    List<PedidoManual> obtenerPedidosManuales();
}
