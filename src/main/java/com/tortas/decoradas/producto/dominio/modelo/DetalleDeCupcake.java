package com.tortas.decoradas.producto.dominio.modelo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class DetalleDeCupcake {

    private Long id_detalle_cupcake;
    private Long cantidadCupcake;
    @Enumerated(EnumType.STRING)
    private Sabor saborCupcake;

    public DetalleDeCupcake() {
    }

    public DetalleDeCupcake(Long id_detalle_cupcake, Long cantidadCupcake, Sabor saborCupcake) {
        this.id_detalle_cupcake = id_detalle_cupcake;
        this.cantidadCupcake = cantidadCupcake;
        this.saborCupcake = saborCupcake;
    }

    public Long getId_detalle_cupcake() {
        return this.id_detalle_cupcake;
    }

    public void setId_detalle_cupcake(Long id_detalle_cupcake) {
        this.id_detalle_cupcake = id_detalle_cupcake;
    }

    public Long getCantidad() {
        return this.cantidadCupcake;
    }

    public void setCantidad(Long cantidadCupcake) {
        this.cantidadCupcake =cantidadCupcake;
    }

    public Sabor getSabor() {
        return this.saborCupcake;
    }

    public void setSabor(Sabor saborCupcake) {
        this.saborCupcake = saborCupcake;
    }

}
