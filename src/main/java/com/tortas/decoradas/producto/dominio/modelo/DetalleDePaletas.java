package com.tortas.decoradas.producto.dominio.modelo;

public class DetalleDePaletas {
    private Long id_detalle_paletas;
    private Long cantidadPaletas;

    public DetalleDePaletas() {
    }

    public DetalleDePaletas(Long id_detalle_paletas, Long cantidadPaletas) {
        this.id_detalle_paletas = id_detalle_paletas;
        this.cantidadPaletas = cantidadPaletas;
    }

    public Long getId_detalle_paletas() {
        return this.id_detalle_paletas;
    }

    public void setId_detalle_paletas(Long id_detalle_paletas) {
        this.id_detalle_paletas = id_detalle_paletas;
    }

    public Long getCantidadPaletas() {
        return this.cantidadPaletas;
    }

    public void setCantidadPaletas(Long cantidadPaletas) {
        this.cantidadPaletas = cantidadPaletas;
    }

}
