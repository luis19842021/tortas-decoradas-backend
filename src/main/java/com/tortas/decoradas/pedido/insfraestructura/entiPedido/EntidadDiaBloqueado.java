package com.tortas.decoradas.pedido.insfraestructura.entiPedido;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "dia_bloqueado", uniqueConstraints = @UniqueConstraint(columnNames = "fecha"))
public class EntidadDiaBloqueado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    private String motivo;

    public EntidadDiaBloqueado() {
    }

    public EntidadDiaBloqueado(LocalDate fecha, String motivo) {
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public DiaBloqueado toDomainModel() {
        return new DiaBloqueado(id, fecha, motivo);
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

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
