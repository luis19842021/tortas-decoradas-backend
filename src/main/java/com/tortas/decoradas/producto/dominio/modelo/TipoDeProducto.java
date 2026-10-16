package com.tortas.decoradas.producto.dominio.modelo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TipoDeProducto {

    private Long id_Tipo_de_producto;
    private DetalleDeTorta detalleDeTorta;
    private DetalleGalleta detalleGalleta;
    private DetalleDeCupcake detalleDeCupcake;
    private DetalleDeCakePops detalleDeCakePops;
    private DetalleDeMinidonas detalleDeMinidonas;
    private DetalleDeOreos detalleDeOreos;
    private DetalleDePaletas detalleDePaletas;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public TipoDeProducto() {
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, DetalleDeTorta detalleDeTorta) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeTorta = detalleDeTorta;
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, DetalleGalleta detalleGalleta) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleGalleta = detalleGalleta;
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, DetalleDeCupcake detalleDeCupcake) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeCupcake = detalleDeCupcake;
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, DetalleDeCakePops detalleDeCakePops) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeCakePops = detalleDeCakePops;
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, DetalleDeMinidonas detalleDeMinidonas) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeMinidonas = detalleDeMinidonas;
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, DetalleDePaletas detalleDePaletas) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDePaletas = detalleDePaletas;
    }

    public TipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, DetalleDeOreos detalleDeOreos) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeOreos = detalleDeOreos;
    }

    public DetalleDeCupcake getDetalleDeCupcake() {
        return this.detalleDeCupcake;
    }

    public void setDetalleDeCupcake(DetalleDeCupcake detalleDeCupcake) {
        this.detalleDeCupcake = detalleDeCupcake;
    }

    public DetalleGalleta getDetalleDeGalleta() {
        return this.detalleGalleta;
    }

    public void setDetalleDeGalleta(DetalleGalleta detalleGalleta) {
        this.detalleGalleta = detalleGalleta;
    }

    public DetalleDeTorta getDetalleDeTorta() {
        return this.detalleDeTorta;
    }

    public void setDetalleDeTorta(DetalleDeTorta detalleDeTorta) {
        this.detalleDeTorta = detalleDeTorta;
    }

    public Long getIdTipo_de_producto() {
        return this.id_Tipo_de_producto;
    }

    public void setIdTipo_de_producto(Long id_Tipo_de_producto) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public DetalleDeCakePops getDetalleDeCakePops() {
        return this.detalleDeCakePops;
    }

    public void setDetalleDeCakePops(DetalleDeCakePops detalleDeCakePops) {
        this.detalleDeCakePops = detalleDeCakePops;
    }
     public DetalleDePaletas getDetalleDePaletas() {
        return this.detalleDePaletas;
    }

    public void setDetalleDePaletas(DetalleDePaletas detalleDePaletas) {
        this.detalleDePaletas = detalleDePaletas;
    }

     public DetalleDeMinidonas getDetalleDeMinidonas() {
        return this.detalleDeMinidonas;
    }

    public void setDetalleDeMinidonas(DetalleDeMinidonas detalleDemDeMinidonas) {
        this.detalleDeMinidonas = detalleDemDeMinidonas;
    }

     public DetalleDeOreos getDetalleDeOreos() {
        return this.detalleDeOreos;
    }

    public void setDetalleDeOreos(DetalleDeOreos detalleDeOreos) {
        this.detalleDeOreos = detalleDeOreos ;
    }

}
