package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.DetalleDeCupcake;
import com.tortas.decoradas.producto.dominio.modelo.Sabor;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_cupcake")
public class EntidadDetalleCupcake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_cupcake;
    private Long cantidadCupcake;
    @Enumerated(EnumType.STRING)
    private Sabor saborCupcake;

    @OneToOne(mappedBy = "detalleDeCupcake", fetch = FetchType.EAGER)
    private EntidadTipoDeProducto tipoDeProductoproducto;

    public EntidadDetalleCupcake() {
    }

    public EntidadDetalleCupcake(Long id_detalle_cupcake, Long cantidadCupcake, Sabor saborCupcake) {
        this.id_detalle_cupcake = id_detalle_cupcake;
        this.cantidadCupcake = cantidadCupcake;
        this.saborCupcake = saborCupcake;
    }

    public DetalleDeCupcake toDomainModel(){
        return new DetalleDeCupcake(id_detalle_cupcake,cantidadCupcake,saborCupcake);
    }

    public EntidadDetalleCupcake fromDomainModel(DetalleDeCupcake detalleDeCupcake){
        return new EntidadDetalleCupcake(detalleDeCupcake.getId_detalle_cupcake(),detalleDeCupcake.getCantidad(),detalleDeCupcake.getSabor());
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
        this.cantidadCupcake = cantidadCupcake;
    }

    public Sabor getSabor() {
        return this.saborCupcake;
    }

    public void setSabor(Sabor saborCupcake) {
        this.saborCupcake = saborCupcake;
    }
}
