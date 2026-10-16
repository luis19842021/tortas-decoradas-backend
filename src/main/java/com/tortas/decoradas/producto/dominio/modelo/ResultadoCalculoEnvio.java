package com.tortas.decoradas.producto.dominio.modelo;

public class ResultadoCalculoEnvio {

    private Long codigoPostal;
    private boolean disponible;
    private String localidad;
    private Long precio;

    public ResultadoCalculoEnvio() {
    }

    public ResultadoCalculoEnvio(Long codigoPostal, boolean disponible, String localidad, Long precio) {
        this.codigoPostal = codigoPostal;
        this.disponible = disponible;
        this.localidad = localidad;
        this.precio = precio;
    }

    public Long getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(Long codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public boolean isDisponible() {
        return this.disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }
}
