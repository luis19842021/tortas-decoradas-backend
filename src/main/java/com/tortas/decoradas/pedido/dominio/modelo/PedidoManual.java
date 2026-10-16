package com.tortas.decoradas.pedido.dominio.modelo;

import java.time.LocalDate;

public class PedidoManual {

    private Long id;
    private LocalDate fecha;
    private int cantidad;
    private String motivo;

    public PedidoManual() {
    }

    public PedidoManual(Long id, LocalDate fecha, int cantidad, String motivo) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
