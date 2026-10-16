package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;

public interface CasoDeUsoReprogramarPedido {
    Pedido reprogramar(String emailUsuario, Long idPedido, LocalDate nuevaFecha);
}
