package com.tortas.decoradas.pedido.dominio.modelo;

import java.time.LocalDate;

public class ReprogramarPedidoRequestDto {

    private LocalDate nuevaFecha;

    public ReprogramarPedidoRequestDto() {
    }

    public LocalDate getNuevaFecha() {
        return this.nuevaFecha;
    }

    public void setNuevaFecha(LocalDate nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }
}
