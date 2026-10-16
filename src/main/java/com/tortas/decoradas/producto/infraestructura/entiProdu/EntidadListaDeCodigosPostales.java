package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lista_codigos_postales")
public class EntidadListaDeCodigosPostales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLista_codigoPostal;
    private Long numero;
    private String localidad;
    private Long precio;

    public EntidadListaDeCodigosPostales() {
    }

    public EntidadListaDeCodigosPostales(Long idLista_codigoPostal, Long numero, String localidad, Long precio) {
        this.idLista_codigoPostal = idLista_codigoPostal;
        this.numero = numero;
        this.localidad = localidad;
        this.precio = precio;
    }

    public ListaDeCodigosPostales toDomainModel() {
        return new ListaDeCodigosPostales(idLista_codigoPostal, numero, localidad, precio);
    }

    public EntidadListaDeCodigosPostales fromDomainModel(ListaDeCodigosPostales listaDeCodigosPostales) {
        return new EntidadListaDeCodigosPostales(listaDeCodigosPostales.getIdLista_codigoPostal(),
                listaDeCodigosPostales.getNumero(), listaDeCodigosPostales.getLocalidad(),
                listaDeCodigosPostales.getPrecio());
    }

    public Long getIdLista_codigoPostal() {
        return this.idLista_codigoPostal;
    }

    public void setIdLista_codigoPostal(Long idLista_codigoPostal) {
        this.idLista_codigoPostal = idLista_codigoPostal;
    }

    public Long getNumero() {
        return this.numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

}
