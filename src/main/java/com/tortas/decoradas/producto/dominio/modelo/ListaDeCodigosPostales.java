package com.tortas.decoradas.producto.dominio.modelo;

public class ListaDeCodigosPostales {

    private Long idLista_codigoPostal;
    private Long numero;
    private String localidad;
    private Long precio;

    public ListaDeCodigosPostales() {
    }

    public ListaDeCodigosPostales(Long idLista_codigoPostal, Long numero, String localidad, Long precio) {
        this.idLista_codigoPostal = idLista_codigoPostal;
        this.numero = numero;
        this.localidad = localidad;
        this.precio = precio;
    }

    public Long getIdLista_codigoPostal() {
        return this.idLista_codigoPostal;
    }

    public void setIdLista_codigoPostal(Long idLista_codigoPostal) {
        this.idLista_codigoPostal = idLista_codigoPostal;
    }

    public Long getNumero() {
        return this.numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
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
