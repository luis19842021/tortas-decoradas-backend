package com.tortas.decoradas.producto.dominio.modelo;

public class ResultadoAjustePrecios {

    private int productos;
    private int tamaños;
    private int bizcochuelos;
    private int categoriasSabor;

    public ResultadoAjustePrecios() {
    }

    public ResultadoAjustePrecios(int productos, int tamaños, int bizcochuelos, int categoriasSabor) {
        this.productos = productos;
        this.tamaños = tamaños;
        this.bizcochuelos = bizcochuelos;
        this.categoriasSabor = categoriasSabor;
    }

    public int getProductos() {
        return productos;
    }

    public void setProductos(int productos) {
        this.productos = productos;
    }

    public int getTamaños() {
        return tamaños;
    }

    public void setTamaños(int tamaños) {
        this.tamaños = tamaños;
    }

    public int getBizcochuelos() {
        return bizcochuelos;
    }

    public void setBizcochuelos(int bizcochuelos) {
        this.bizcochuelos = bizcochuelos;
    }

    public int getCategoriasSabor() {
        return categoriasSabor;
    }

    public void setCategoriasSabor(int categoriasSabor) {
        this.categoriasSabor = categoriasSabor;
    }

}
