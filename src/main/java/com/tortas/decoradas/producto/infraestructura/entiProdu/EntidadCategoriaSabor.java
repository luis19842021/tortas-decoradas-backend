package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria_sabor")
public class EntidadCategoriaSabor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaSabor;
    private String nombre;
    private Long precio;

    public EntidadCategoriaSabor() {
    }

    public EntidadCategoriaSabor(Long idCategoriaSabor, String nombre, Long precio) {
        this.idCategoriaSabor = idCategoriaSabor;
        this.nombre = nombre;
        this.precio = precio;
    }

    public CategoriaSabor toDomainModel() {
        return new CategoriaSabor(idCategoriaSabor, nombre, precio);
    }

    public EntidadCategoriaSabor fromDomainModel(CategoriaSabor categoriaSabor) {
        return new EntidadCategoriaSabor(categoriaSabor.getIdCategoriaSabor(), categoriaSabor.getNombre(),
                categoriaSabor.getPrecio());
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
