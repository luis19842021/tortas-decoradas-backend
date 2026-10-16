package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.DetalleDeOreos;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_oreos")
public class EntidadDetalleOreos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_oreos;
    private Long cantidadOreos;

    @OneToOne(mappedBy = "detalleOreos", fetch = FetchType.EAGER)
    private EntidadTipoDeProducto tipoDeProductoproducto;

    public EntidadDetalleOreos() {
    }

    public EntidadDetalleOreos(Long id_detalle_oreos, Long cantidadOreos) {
        this.id_detalle_oreos = id_detalle_oreos;
        this.cantidadOreos = cantidadOreos;
    }

    public DetalleDeOreos toDomainModel() {
        return new DetalleDeOreos(id_detalle_oreos, cantidadOreos);
    }

    public EntidadDetalleOreos fromDomainModel(DetalleDeOreos detalleDeOreos) {
        return new EntidadDetalleOreos(detalleDeOreos.getId_detalle_oreos(), detalleDeOreos.getCantidadOreos());
    }

    public Long getId_detalle_oroes() {
        return this.id_detalle_oreos;
    }

    public void setId_detalle_oroes(Long id_detalle_oreos) {
        this.id_detalle_oreos = id_detalle_oreos;
    }

    public Long getCantidadOreos() {
        return this.cantidadOreos;
    }

    public void setCantidadOreos(Long cantidadOreos) {
        this.cantidadOreos = cantidadOreos;
    }

}
