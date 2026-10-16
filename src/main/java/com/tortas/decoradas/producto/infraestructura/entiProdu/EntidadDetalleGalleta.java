package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.DetalleGalleta;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_galleta")
public class EntidadDetalleGalleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_galleta;
    private String saborGalleta;
    private Long cantidadGalleta;

    @OneToOne(mappedBy = "detalleDeGalleta", fetch = FetchType.EAGER)
    private EntidadTipoDeProducto tipoDeProductoproducto;

    public EntidadDetalleGalleta() {
    }

    public EntidadDetalleGalleta(Long id_detalle_galleta, String saborGalleta, Long cantidadGalleta) {
        this.id_detalle_galleta = id_detalle_galleta;
        this.saborGalleta = saborGalleta;
        this.cantidadGalleta = cantidadGalleta;
    }

    public DetalleGalleta toDomainModel(){
        return new DetalleGalleta(id_detalle_galleta,saborGalleta,cantidadGalleta);
    }

    public  EntidadDetalleGalleta fromDomainModel(DetalleGalleta detalleDeGalleta) {

        return new EntidadDetalleGalleta(detalleDeGalleta.getId_detalle_galleta(),detalleDeGalleta.getSabor(),detalleDeGalleta.getCantidad() );
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
                " sabor='" + getSabor() + "'" +
                ", cantidad='" + getCantidad() + "'" +
                "}";
    }

}
