package com.tortas.decoradas.pedido.dominio.puerto.in;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;

public interface CasoDeUsoCancelarPedido {
    Pedido cancelar(String emailUsuario, Long idPedido);
}
