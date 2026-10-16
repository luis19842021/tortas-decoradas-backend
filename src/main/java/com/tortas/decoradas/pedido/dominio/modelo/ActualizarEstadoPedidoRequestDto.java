package com.tortas.decoradas.pedido.dominio.modelo;

public class ActualizarEstadoPedidoRequestDto {

    private Estado estado;

    public ActualizarEstadoPedidoRequestDto() {
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
