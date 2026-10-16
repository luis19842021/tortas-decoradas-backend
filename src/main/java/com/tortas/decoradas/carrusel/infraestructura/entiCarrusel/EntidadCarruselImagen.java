package com.tortas.decoradas.carrusel.infraestructura.entiCarrusel;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrusel_imagen")
public class EntidadCarruselImagen {

    @Id
    private Integer espacio;

    private String url;

    @Column(length = 300)
    private String enlace;

    public EntidadCarruselImagen() {
    }

    public EntidadCarruselImagen(Integer espacio, String url) {
        this.espacio = espacio;
        this.url = url;
    }

    public static EntidadCarruselImagen fromDomainModel(ImagenCarrusel imagenCarrusel) {
        return new EntidadCarruselImagen(imagenCarrusel.getEspacio(), imagenCarrusel.getUrl());
    }

    public ImagenCarrusel toDomainModel() {
        return new ImagenCarrusel(this.espacio, this.url, this.enlace);
    }

    public String getEnlace() {
        return this.enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public Integer getEspacio() {
        return this.espacio;
    }

    public void setEspacio(Integer espacio) {
        this.espacio = espacio;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
