package com.tortas.decoradas.producto.dominio.modelo;

public class DetalleDeCakePops {

    private Long id_detalle_cakePops;
    private Long cantidadCakePops;

    public DetalleDeCakePops() {
    }

    public DetalleDeCakePops(Long id_detalle_cakePops, Long cantidadCakePops) {
        this.id_detalle_cakePops = id_detalle_cakePops;
        this.cantidadCakePops = cantidadCakePops;
    }

    public Long getId_detalle_cakePops() {
        return this.id_detalle_cakePops;
    }

    public void setId_detalle_cakePops(Long id_detalle_cakePops) {
        this.id_detalle_cakePops = id_detalle_cakePops;
    }

    public Long getCantidadCakePops() {
        return this.cantidadCakePops;
    }

    public void setCantidadCakePops(Long cantidadCakePops) {
        this.cantidadCakePops = cantidadCakePops;
    }

}
