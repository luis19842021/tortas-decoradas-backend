package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;

public interface CasoDeUsoObtenerPedidos {

    List<Pedido> obtenerTodosLosPedidos();

}
