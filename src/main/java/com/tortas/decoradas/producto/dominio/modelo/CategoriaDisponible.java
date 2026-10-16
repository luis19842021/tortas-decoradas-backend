package com.tortas.decoradas.producto.dominio.modelo;

public class CategoriaDisponible {

    private Categoria categoria;
    private Tipo tipo;

    public CategoriaDisponible() {
    }

    public CategoriaDisponible(Categoria categoria, Tipo tipo) {
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
