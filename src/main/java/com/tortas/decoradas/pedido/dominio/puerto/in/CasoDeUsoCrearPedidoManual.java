package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;

public interface CasoDeUsoCrearPedidoManual {
    PedidoManual crearPedidoManual(LocalDate fecha, int cantidad, String motivo);
}
