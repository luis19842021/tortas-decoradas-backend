package com.tortas.decoradas.usuario.dominio.modelo;

public class CambiarContraseniaRequestDto {

    private String contraseniaActual;
    private String contraseniaNueva;

    public CambiarContraseniaRequestDto() {
    }

    public String getContraseniaActual() {
        return this.contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
    }

    public String getContraseniaNueva() {
        return this.contraseniaNueva;
    }

    public void setContraseniaNueva(String contraseniaNueva) {
        this.contraseniaNueva = contraseniaNueva;
    }
}
