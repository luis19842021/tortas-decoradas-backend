package com.tortas.decoradas.producto.dominio.modelo;

public class CambiarEstadoProductoRequestDto {

    private boolean suspendido;

    public CambiarEstadoProductoRequestDto() {
    }

    public CambiarEstadoProductoRequestDto(boolean suspendido) {
        this.suspendido = suspendido;
    }

    public boolean isSuspendido() {
        return this.suspendido;
    }

    public void setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
    }

}
