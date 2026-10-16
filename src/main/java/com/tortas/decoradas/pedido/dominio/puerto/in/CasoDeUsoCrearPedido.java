package com.tortas.decoradas.pedido.dominio.puerto.in;

import com.tortas.decoradas.pedido.dominio.modelo.CrearPedidoRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;

public interface CasoDeUsoCrearPedido {

    Pedido crearPedido(String emailUsuario, CrearPedidoRequestDto datos);
}
