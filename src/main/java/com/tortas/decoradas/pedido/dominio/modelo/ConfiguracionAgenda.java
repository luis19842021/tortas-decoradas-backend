package com.tortas.decoradas.pedido.dominio.modelo;

public class ConfiguracionAgenda {

    private Long id;
    private int cupoMaximoPorDia;

    public ConfiguracionAgenda() {
    }

    public ConfiguracionAgenda(Long id, int cupoMaximoPorDia) {
        this.id = id;
        this.cupoMaximoPorDia = cupoMaximoPorDia;
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
