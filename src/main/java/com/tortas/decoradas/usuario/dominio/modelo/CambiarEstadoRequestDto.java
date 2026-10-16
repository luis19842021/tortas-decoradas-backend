package com.tortas.decoradas.usuario.dominio.modelo;

public class CambiarEstadoRequestDto {

    private boolean suspendida;

    public CambiarEstadoRequestDto() {
    }

    public CambiarEstadoRequestDto(boolean suspendida) {
        this.suspendida = suspendida;
    }

    public boolean isSuspendida() {
        return this.suspendida;
    }

    public void setSuspendida(boolean suspendida) {
        this.suspendida = suspendida;
    }

}
