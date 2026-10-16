package com.tortas.decoradas.pedido.dominio.modelo;

import java.time.LocalDate;

public class BloquearDiaRequestDto {

    private LocalDate fecha;
    private String motivo;

    public BloquearDiaRequestDto() {
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
