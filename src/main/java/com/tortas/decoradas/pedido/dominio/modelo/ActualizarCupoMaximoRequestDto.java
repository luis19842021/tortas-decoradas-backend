package com.tortas.decoradas.pedido.dominio.modelo;

public class ActualizarCupoMaximoRequestDto {

    private int cupoMaximoPorDia;

    public ActualizarCupoMaximoRequestDto() {
    }

    public int getCupoMaximoPorDia() {
        return this.cupoMaximoPorDia;
    }

    public void setCupoMaximoPorDia(int cupoMaximoPorDia) {
        this.cupoMaximoPorDia = cupoMaximoPorDia;
    }
}
