package com.tortas.decoradas.producto.dominio.modelo;

import java.util.Set;

public class ListaDeBizcochuelos {

    private Long idLista_bizcochuelo;
    private String bizcochuelo;
    private Long precio;

    public ListaDeBizcochuelos() {
    }

    public ListaDeBizcochuelos(Long idLista_bizcochuelo, String bizcochuelo, Long precio) {
        this.idLista_bizcochuelo = idLista_bizcochuelo;
        this.bizcochuelo = bizcochuelo;
        this.precio = precio;
    }

    public Long getIdLista_bizcochuelo() {
        return this.idLista_bizcochuelo;
    }

    public void setIdLista_bizcochuelo(Long idLista_bizcochuelo) {
        this.idLista_bizcochuelo = idLista_bizcochuelo;
    }

    public String getBizcochuelo() {
        return this.bizcochuelo;
    }

    public void setBizcochuelo(String bizcochuelo) {
        this.bizcochuelo = bizcochuelo;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    private Set<Alergeno> alergenos;

    public Set<Alergeno> getAlergenos() {
        return this.alergenos;
    }

    public void setAlergenos(Set<Alergeno> alergenos) {
        this.alergenos = alergenos;
    }

}
