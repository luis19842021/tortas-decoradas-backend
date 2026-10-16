package com.tortas.decoradas.producto.dominio.modelo;

public class DetalleDeMinidonas {
    private Long id_detalle_minidonas;
    private Long cantidadMinidonas;

    public DetalleDeMinidonas() {
    }

    public DetalleDeMinidonas(Long id_detalle_minidonas, Long cantidadMinidonas) {
        this.id_detalle_minidonas = id_detalle_minidonas;
        this.cantidadMinidonas = cantidadMinidonas;
    }

    public Long getId_detalle_minidonas() {
        return this.id_detalle_minidonas;
    }

    public void setId_detalle_minidonas(Long id_detalle_minidonas) {
        this.id_detalle_minidonas = id_detalle_minidonas;
    }

    public Long getCantidadMinidonas() {
        return this.cantidadMinidonas;
    }

    public void setCantidadMinidonas(Long cantidadMinidonas) {
        this.cantidadMinidonas = cantidadMinidonas;
    }
}
