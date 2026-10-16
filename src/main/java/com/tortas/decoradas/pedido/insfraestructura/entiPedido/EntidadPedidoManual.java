package com.tortas.decoradas.pedido.insfraestructura.entiPedido;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_manual")
public class EntidadPedidoManual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private int cantidad;

    private String motivo;

    public EntidadPedidoManual() {
    }

    public EntidadPedidoManual(LocalDate fecha, int cantidad, String motivo) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public PedidoManual toDomainModel() {
        return new PedidoManual(id, fecha, cantidad, motivo);
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
