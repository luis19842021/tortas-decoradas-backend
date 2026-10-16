package com.tortas.decoradas.pedido.insfraestructura.entiPedido;

import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "configuracion_agenda")
public class EntidadConfiguracionAgenda {

    @Id
    private Long id;

    private int cupoMaximoPorDia;

    public EntidadConfiguracionAgenda() {
    }

    public EntidadConfiguracionAgenda(Long id, int cupoMaximoPorDia) {
        this.id = id;
        this.cupoMaximoPorDia = cupoMaximoPorDia;
    }

    public ConfiguracionAgenda toDomainModel() {
        return new ConfiguracionAgenda(id, cupoMaximoPorDia);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCupoMaximoPorDia() {
        return this.cupoMaximoPorDia;
    }

    public void setCupoMaximoPorDia(int cupoMaximoPorDia) {
        this.cupoMaximoPorDia = cupoMaximoPorDia;
    }
}
