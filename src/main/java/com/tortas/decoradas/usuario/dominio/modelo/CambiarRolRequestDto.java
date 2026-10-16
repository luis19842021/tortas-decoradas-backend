package com.tortas.decoradas.usuario.dominio.modelo;

public class CambiarRolRequestDto {

    private String rol;

    public CambiarRolRequestDto() {
    }

    public CambiarRolRequestDto(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
