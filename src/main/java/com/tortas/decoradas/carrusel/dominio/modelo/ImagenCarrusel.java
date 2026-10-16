package com.tortas.decoradas.carrusel.dominio.modelo;

public class ImagenCarrusel {

    private Integer espacio;
    private String url;
    private String enlace;

    public ImagenCarrusel() {
    }

    public ImagenCarrusel(Integer espacio, String url) {
        this.espacio = espacio;
        this.url = url;
    }

    public ImagenCarrusel(Integer espacio, String url, String enlace) {
        this.espacio = espacio;
        this.url = url;
        this.enlace = enlace;
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
