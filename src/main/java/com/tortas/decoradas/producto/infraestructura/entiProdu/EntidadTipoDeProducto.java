package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.Tipo;
import com.tortas.decoradas.producto.dominio.modelo.TipoDeProducto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_de_producto")
public class EntidadTipoDeProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Tipo_de_producto;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_torta", referencedColumnName = "id_detalle_torta", unique = true)
    private EntidadDetalleDeTorta detalleDeTorta;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_galleta", referencedColumnName = "id_detalle_galleta")
    private EntidadDetalleGalleta detalleDeGalleta;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_cupcake", referencedColumnName = "id_detalle_cupcake")
    private EntidadDetalleCupcake detalleDeCupcake;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_cakepops", referencedColumnName = "id_detalle_cakepops")
    private EntidadDetalleCakePops detalleCakePops;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_paletas", referencedColumnName = "id_detalle_paletas")
    private EntidadDetallePaletas detallePaletas;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_oreos", referencedColumnName = "id_detalle_oreos")
    private EntidadDetalleOreos detalleOreos;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_minidonas", referencedColumnName = "id_detalle_minidonas")
    private EntidadDetalleMinidonas detalleMinidonas;

    public EntidadTipoDeProducto() {
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, EntidadDetalleDeTorta detalleDeTorta) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeTorta = detalleDeTorta;
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, EntidadDetalleGalleta detalleDeGalleta) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeGalleta = detalleDeGalleta;
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, EntidadDetalleCupcake detalleDeCupcake) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleDeCupcake = detalleDeCupcake;
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, EntidadDetalleCakePops detalleDeCakePops) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleCakePops = detalleDeCakePops;
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, EntidadDetallePaletas detalleDePaletas) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detallePaletas = detalleDePaletas;
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, EntidadDetalleOreos detalleDeOreos) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleOreos = detalleDeOreos;
    }

    public EntidadTipoDeProducto(Long id_Tipo_de_producto, Tipo tipo, EntidadDetalleMinidonas detalleDemMinidonas) {
        this.id_Tipo_de_producto = id_Tipo_de_producto;
        this.tipo = tipo;
        this.detalleMinidonas = detalleDemMinidonas;
    }

    public TipoDeProducto toDomainModel() {

        if (tipo == Tipo.TORTA) {
            return new TipoDeProducto(id_Tipo_de_producto, tipo, detalleDeTorta.toDomainModel());
        }
        if (tipo == Tipo.GALLETA) {
            return new TipoDeProducto(id_Tipo_de_producto, tipo, detalleDeGalleta.toDomainModel());
        }
        if (tipo == Tipo.CUPCAKE) {
            return new TipoDeProducto(id_Tipo_de_producto, tipo, detalleDeCupcake.toDomainModel());
        }
        if (tipo == Tipo.CAKEPOPS) {
            return new TipoDeProducto(id_Tipo_de_producto, tipo, detalleCakePops.toDomainModel());
        }
        if (tipo == Tipo.MINIDONAS) {
            return new TipoDeProducto(id_Tipo_de_producto, tipo, detalleMinidonas.toDomainModel());
        }
        if (tipo == Tipo.OREOS) {
            return new TipoDeProducto(id_Tipo_de_producto, tipo, detalleOreos.toDomainModel());
        }
        if (tipo == Tipo.PALETAS) {
            return new TipoDeProducto(id_Tipo_de_producto, tipo, detallePaletas.toDomainModel());
        }
        return new TipoDeProducto(id_Tipo_de_producto, tipo);

    }

    public EntidadTipoDeProducto fromDomainModel(TipoDeProducto tipoDeProducto) {

        switch (tipoDeProducto.getTipo()) {
            case TORTA:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo(),
                        new EntidadDetalleDeTorta().fromDomainModel(tipoDeProducto.getDetalleDeTorta()));
            case GALLETA:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo(),
                        new EntidadDetalleGalleta().fromDomainModel(tipoDeProducto.getDetalleDeGalleta()));
            case CUPCAKE:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo(),
                        new EntidadDetalleCupcake().fromDomainModel(tipoDeProducto.getDetalleDeCupcake()));
            case CAKEPOPS:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo(),
                        new EntidadDetalleCakePops().fromDomainModel(tipoDeProducto.getDetalleDeCakePops()));
            case PALETAS:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo(),
                        new EntidadDetallePaletas().fromDomainModel(tipoDeProducto.getDetalleDePaletas()));
            case OREOS:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo(),
                        new EntidadDetalleOreos().fromDomainModel(tipoDeProducto.getDetalleDeOreos()));
            case MINIDONAS:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo(),
                        new EntidadDetalleMinidonas().fromDomainModel(tipoDeProducto.getDetalleDeMinidonas()));

            default:
                return new EntidadTipoDeProducto(tipoDeProducto.getIdTipo_de_producto(), tipoDeProducto.getTipo());

        }

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

    public EntidadDetalleDeTorta getDetalleDeTorta() {
        return this.detalleDeTorta;
    }

    public void setDetalleDeTorta(EntidadDetalleDeTorta detalleDeTorta) {
        this.detalleDeTorta = detalleDeTorta;
    }

    public EntidadDetalleGalleta getDetalleDeGalleta() {
        return this.detalleDeGalleta;
    }

    public void setDetalleDeGalleta(EntidadDetalleGalleta detalleDeGalleta) {
        this.detalleDeGalleta = detalleDeGalleta;
    }

    public EntidadDetalleCupcake getDetalleDeCupcake() {
        return this.detalleDeCupcake;
    }

    public void setDetalleDeCupCake(EntidadDetalleCupcake detalleDeCupcake) {
        this.detalleDeCupcake = detalleDeCupcake;
    }

    public EntidadDetalleCakePops getDetalleDeCakePops() {
        return this.detalleCakePops;
    }

    public void setDetalleDeCakePops(EntidadDetalleCakePops detalleCakePops) {
        this.detalleCakePops = detalleCakePops;
    }

    public EntidadDetallePaletas getDetalleDePaletas() {
        return this.detallePaletas;
    }

    public void setDetalleDePaletas(EntidadDetallePaletas detallePaletas) {
        this.detallePaletas = detallePaletas;
    }

    public EntidadDetalleOreos getDetalleDeOreos() {
        return this.detalleOreos;
    }

    public void setDetalleDeOreos(EntidadDetalleOreos detalleOreos) {
        this.detalleOreos = detalleOreos;
    }

    public EntidadDetalleMinidonas getDetalleDeMinidonas() {
        return this.detalleMinidonas;
    }

    public void setDetalleDeMnidonas(EntidadDetalleMinidonas detalleMinidonas) {
        this.detalleMinidonas = detalleMinidonas
        ;
    }

}
