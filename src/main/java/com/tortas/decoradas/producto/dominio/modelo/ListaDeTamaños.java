package com.tortas.decoradas.producto.dominio.modelo;

public class ListaDeTamaños {

    private Long idLista_tamaño;
    private String medida;

    private String forma;
    private Long precio;

    public ListaDeTamaños() {
    }

    public ListaDeTamaños(Long idLista_tamaño, String medida, String forma, Long precio) {
        this.idLista_tamaño = idLista_tamaño;
        this.medida = medida;
        this.forma = forma;
        this.precio = precio;
    }

    public Long getIdLista_tamaño() {
        return this.idLista_tamaño;
    }

    public void setIdLista_tamaño(Long idLista_tamaño) {
        this.idLista_tamaño = idLista_tamaño;
    }

    public String getMedida() {
        return this.medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getForma() {
        return this.forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

}
