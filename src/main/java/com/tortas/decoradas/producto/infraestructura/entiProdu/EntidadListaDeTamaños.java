package com.tortas.decoradas.producto.infraestructura.entiProdu;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lista_tamanos")
public class EntidadListaDeTamaños {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLista_tamaño;
    private String medida;
    private String forma;
    private Long precio;

    public EntidadListaDeTamaños() {
    }

    public EntidadListaDeTamaños(Long idLista_tamaño, String medida, String forma, Long precio) {
        this.idLista_tamaño = idLista_tamaño;
        this.medida = medida;
        this.forma = forma;
        this.precio = precio;
    }

    public ListaDeTamaños toDomainModel() {
        return new ListaDeTamaños(idLista_tamaño, medida, forma, precio);
    }

    public EntidadListaDeTamaños fromDomainModel(ListaDeTamaños listaDeTamaños) {
        return new EntidadListaDeTamaños(listaDeTamaños.getIdLista_tamaño(), listaDeTamaños.getMedida(),
                listaDeTamaños.getForma(), listaDeTamaños.getPrecio());
    }

    public Long getIdLista_tamaño() {
        return this.idLista_tamaño;
    }

    public void setIdLista_tamaño(Long idLista_tamaño) {
        this.idLista_tamaño = idLista_tamaño;
    }

    public String getMedida() {
        return this.medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getForma() {
        return this.forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

}
