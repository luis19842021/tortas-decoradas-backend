package com.tortas.decoradas.pedido.dominio.puerto.in;

import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;

public interface CasoDeUsoActualizarEstadoPedido {

    Pedido actualizarEstado(String emailUsuario, Long idPedido, Estado nuevoEstado);
}
