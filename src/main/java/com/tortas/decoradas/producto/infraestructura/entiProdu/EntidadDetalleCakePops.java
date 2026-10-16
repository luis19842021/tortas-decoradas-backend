package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.DetalleDeCakePops;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_cakepops")
public class EntidadDetalleCakePops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_cakepops;
    private Long cantidadCakepops;

      @OneToOne(mappedBy = "detalleCakePops", fetch = FetchType.EAGER)
    private EntidadTipoDeProducto tipoDeProductoproducto;

    public EntidadDetalleCakePops() {
    }

    public EntidadDetalleCakePops(Long id_detalle_cakepops, Long cantidadCakepops) {
        this.id_detalle_cakepops = id_detalle_cakepops;
        this.cantidadCakepops = cantidadCakepops;
    }

     public DetalleDeCakePops toDomainModel(){
        return new DetalleDeCakePops(id_detalle_cakepops,cantidadCakepops);
    }

    public EntidadDetalleCakePops fromDomainModel(DetalleDeCakePops detalleDeCakePops){
        return new EntidadDetalleCakePops(detalleDeCakePops.getId_detalle_cakePops(),detalleDeCakePops.getCantidadCakePops());
    }

    public Long getId_detalle_cakepops() {
        return this.id_detalle_cakepops;
    }

    public void setId_detalle_cakepops(Long id_detalle_cakepops) {
        this.id_detalle_cakepops = id_detalle_cakepops;
    }

    public Long getCantidadCakepops() {
        return this.cantidadCakepops;
    }

    public void setCantidadCakepops(Long cantidadCakepops) {
        this.cantidadCakepops = cantidadCakepops;
    }

}
