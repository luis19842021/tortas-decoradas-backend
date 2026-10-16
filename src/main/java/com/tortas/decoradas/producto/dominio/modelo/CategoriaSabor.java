package com.tortas.decoradas.producto.dominio.modelo;

public class CategoriaSabor {

    private Long idCategoriaSabor;
    private String nombre;
    private Long precio;

    public CategoriaSabor() {
    }

    public CategoriaSabor(Long idCategoriaSabor, String nombre, Long precio) {
        this.idCategoriaSabor = idCategoriaSabor;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getIdCategoriaSabor() {
        return this.idCategoriaSabor;
    }

    public void setIdCategoriaSabor(Long idCategoriaSabor) {
        this.idCategoriaSabor = idCategoriaSabor;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

}
