package com.tortas.decoradas.producto.dominio.modelo;

public class DetalleGalleta {

    private Long id_detalle_galleta;
    private String saborGalleta;
    private Long cantidadGalleta;

    public DetalleGalleta() {
    }

     public DetalleGalleta(Long id_detalle_galleta, String saborGalleta, Long cantidadGalleta) {
        this.id_detalle_galleta = id_detalle_galleta;
        this.saborGalleta = saborGalleta;
        this.cantidadGalleta = cantidadGalleta;
    }

     public Long getId_detalle_galleta() {
        return this.id_detalle_galleta;
    }

    public void setId_detalle_galleta(Long id_detalle_galleta) {
        this.id_detalle_galleta = id_detalle_galleta;
    }

    public String getSabor() {
        return this.saborGalleta;
    }

    public void setSabor(String saborGalleta) {
        this.saborGalleta = saborGalleta;
    }

    public Long getCantidad() {
        return this.cantidadGalleta;
    }

    public void setCantidad(Long cantidadGalleta) {
        this.cantidadGalleta = cantidadGalleta;
    }

    @Override
    public String toString() {
        return "{" +
            " id_detalle_galleta='" + getId_detalle_galleta() + "'" +
            ", sabor='" + getSabor() + "'" +
            ", cantidad='" + getCantidad() + "'" +
            "}";
    }

}
