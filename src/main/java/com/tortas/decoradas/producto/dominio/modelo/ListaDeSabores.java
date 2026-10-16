package com.tortas.decoradas.producto.dominio.modelo;

import java.util.Set;

public class ListaDeSabores {

private Long idLista_sabor;
private String sabor;
private CategoriaSabor categoria;

    public ListaDeSabores() {
    }

    public ListaDeSabores(Long idLista_sabor, String sabor, CategoriaSabor categoria) {
        this.idLista_sabor = idLista_sabor;
        this.sabor = sabor;
        this.categoria = categoria;
    }

    public Long getIdLista_sabor() {
        return this.idLista_sabor;
    }

    public void setIdLista_sabor(Long idLista_sabor) {
        this.idLista_sabor = idLista_sabor;
    }

    public String getSabor() {
        return this.sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public CategoriaSabor getCategoria() {
        return this.categoria;
    }

    public void setCategoria(CategoriaSabor categoria) {
        this.categoria = categoria;
    }

    private Set<Alergeno> alergenos;

    public Set<Alergeno> getAlergenos() {
        return this.alergenos;
    }

    public void setAlergenos(Set<Alergeno> alergenos) {
        this.alergenos = alergenos;
    }

}
