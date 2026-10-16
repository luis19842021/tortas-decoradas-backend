package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;

public interface CasoDeUsoObtenerPedidosDeUsuario {

    List<Pedido> obtenerPedidosDeUsuario(String emailUsuario);

}
