package com.tortas.decoradas.producto.infraestructura.entiProdu;

import java.util.HashSet;
import java.util.Set;
import com.tortas.decoradas.producto.dominio.modelo.Alergeno;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lista_bizcochuelos")
public class EntidadListaDeBizcochuelos {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bizcochuelo_alergeno", joinColumns = @JoinColumn(name = "id_lista_bizcochuelo"))
    @Enumerated(EnumType.STRING)
    @Column(name = "alergeno", length = 20)
    private Set<Alergeno> alergenos = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLista_bizcochuelo;
    private String bizcochuelo;
    private Long precio;

    public EntidadListaDeBizcochuelos() {
    }

    public EntidadListaDeBizcochuelos(Long idLista_bizcochuelo, String bizcochuelo, Long precio) {
        this.idLista_bizcochuelo = idLista_bizcochuelo;
        this.bizcochuelo = bizcochuelo;
        this.precio = precio;
    }

    public ListaDeBizcochuelos toDomainModel() {
        ListaDeBizcochuelos dominio = new ListaDeBizcochuelos(idLista_bizcochuelo, bizcochuelo, precio);
        dominio.setAlergenos(new HashSet<>(alergenos));
        return dominio;
    }

    public EntidadListaDeBizcochuelos fromDomainModel(ListaDeBizcochuelos listaDeBizcochuelos) {
        EntidadListaDeBizcochuelos entidad = new EntidadListaDeBizcochuelos(listaDeBizcochuelos.getIdLista_bizcochuelo(),
                listaDeBizcochuelos.getBizcochuelo(), listaDeBizcochuelos.getPrecio());
        entidad.alergenos = listaDeBizcochuelos.getAlergenos() == null ? new HashSet<>() : new HashSet<>(listaDeBizcochuelos.getAlergenos());
        return entidad;
    }

    public Long getIdLista_bizcochuelo() {
        return this.idLista_bizcochuelo;
    }

    public void setIdLista_bizcochuelo(Long idLista_bizcochuelo) {
        this.idLista_bizcochuelo = idLista_bizcochuelo;
    }

    public String getBizcochuelo() {
        return this.bizcochuelo;
    }

    public void setBizcochuelo(String bizcochuelo) {
        this.bizcochuelo = bizcochuelo;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Set<Alergeno> getAlergenos() {
        return this.alergenos;
    }

    public void setAlergenos(Set<Alergeno> alergenos) {
        this.alergenos = alergenos;
    }

}
