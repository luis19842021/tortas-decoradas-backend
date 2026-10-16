package com.tortas.decoradas.pedido.dominio.puerto.in;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;

public interface CasoDeUsoMarcarEntregado {

    Pedido marcarEntregado(Long idPedido);

}
