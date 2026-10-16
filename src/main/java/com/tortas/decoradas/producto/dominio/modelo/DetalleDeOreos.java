package com.tortas.decoradas.producto.dominio.modelo;

public class DetalleDeOreos {

        private Long id_detalle_oreos;
    private Long cantidadOreos;

    public DetalleDeOreos() {
    }

    public DetalleDeOreos(Long id_detalle_oreos, Long cantidadOreos) {
        this.id_detalle_oreos = id_detalle_oreos;
        this.cantidadOreos = cantidadOreos;
    }

    public Long getId_detalle_oreos() {
        return this.id_detalle_oreos;
    }

    public void setId_detalle_oreos(Long id_detalle_oreos) {
        this.id_detalle_oreos = id_detalle_oreos;
    }

    public Long getCantidadOreos() {
        return this.cantidadOreos;
    }

    public void setCantidadOreos(Long cantidadOreos) {
        this.cantidadOreos = cantidadOreos;
    }

}
