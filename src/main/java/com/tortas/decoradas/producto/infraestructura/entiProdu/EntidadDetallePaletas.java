package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.DetalleDePaletas;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "detalle_paletas")
public class EntidadDetallePaletas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_paletas;
    private Long cantidadPaletas;

    @OneToOne(mappedBy = "detallePaletas", fetch = FetchType.EAGER)
    private EntidadTipoDeProducto tipoDeProductoproducto;

    public EntidadDetallePaletas() {
    }

    public EntidadDetallePaletas(Long id_detalle_paletas, Long cantidadPaletas) {
        this.id_detalle_paletas = id_detalle_paletas;
        this.cantidadPaletas = cantidadPaletas;
    }

    public DetalleDePaletas toDomainModel() {
        return new DetalleDePaletas(id_detalle_paletas, cantidadPaletas);
    }

    public EntidadDetallePaletas fromDomainModel(DetalleDePaletas detalleDePaletas) {
        return new EntidadDetallePaletas(detalleDePaletas.getId_detalle_paletas(),
                detalleDePaletas.getCantidadPaletas());
    }

    public Long getId_detalle_paletas() {
        return this.id_detalle_paletas;
    }

    public void setId_detalle_paletas(Long id_detalle_paletas) {
        this.id_detalle_paletas = id_detalle_paletas;
    }

    public Long getCantidadPaletas() {
        return this.cantidadPaletas;
    }

    public void setCantidadPaletas(Long cantidadPaletas) {
        this.cantidadPaletas = cantidadPaletas;
    }

}
