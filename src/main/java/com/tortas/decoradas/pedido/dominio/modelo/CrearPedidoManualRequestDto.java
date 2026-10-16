package com.tortas.decoradas.pedido.dominio.modelo;

import java.time.LocalDate;

public class CrearPedidoManualRequestDto {

    private LocalDate fecha;
    private int cantidad;
    private String motivo;

    public CrearPedidoManualRequestDto() {
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
