package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.DetalleDeMinidonas;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "detalle_minidonas")
public class EntidadDetalleMinidonas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_minidonas;
    private Long cantidadMinidonas;

    @OneToOne(mappedBy = "detalleMinidonas", fetch = FetchType.EAGER)
    private EntidadTipoDeProducto tipoDeProductoproducto;

    public EntidadDetalleMinidonas() {
    }

    public EntidadDetalleMinidonas(Long id_detalle_minidonas, Long cantidadMinidonas) {
        this.id_detalle_minidonas = id_detalle_minidonas;
        this.cantidadMinidonas = cantidadMinidonas;
    }

    public DetalleDeMinidonas toDomainModel() {
        return new DetalleDeMinidonas(id_detalle_minidonas, cantidadMinidonas);
    }

    public EntidadDetalleMinidonas fromDomainModel(DetalleDeMinidonas detalleDeMinidonas) {
        return new EntidadDetalleMinidonas(detalleDeMinidonas.getId_detalle_minidonas(),
                detalleDeMinidonas.getCantidadMinidonas());
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
