package com.tortas.decoradas.producto.infraestructura.entiProdu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tortas.decoradas.producto.dominio.modelo.DetalleDeTorta;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_torta")

public class EntidadDetalleDeTorta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_torta;
    private Long pisos;
    private Boolean estructura;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "relleno", joinColumns = @JoinColumn(name = "id_detalle_torta", referencedColumnName = "id_detalle_torta"), inverseJoinColumns = @JoinColumn(name = "idLista_sabor", referencedColumnName = "idLista_sabor"))
    private List<EntidadListaDeSabores> sabores;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tamano")
    private EntidadListaDeTamaños tamaño;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tamano_dos")
    private EntidadListaDeTamaños tamañoDos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tamano_tres")
    private EntidadListaDeTamaños tamañoTres;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bizcochuelo")
    private EntidadListaDeBizcochuelos bizcochuelo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bizcochuelo_dos")
    private EntidadListaDeBizcochuelos bizcochueloDos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bizcochuelo_tres")
    private EntidadListaDeBizcochuelos bizcochueloTres;

    @OneToOne(mappedBy = "detalleDeTorta", fetch = FetchType.EAGER)
    private EntidadTipoDeProducto tipoDeProductoproducto;

    public EntidadDetalleDeTorta() {
    }

    public EntidadDetalleDeTorta(Long id_detalle_torta, Long pisos, Boolean estructura,
            List<EntidadListaDeSabores> sabores, EntidadListaDeTamaños tamaño,
            EntidadListaDeTamaños tamañoDos, EntidadListaDeTamaños tamañoTres,
            EntidadListaDeBizcochuelos bizcochuelo, EntidadListaDeBizcochuelos bizcochueloDos,
            EntidadListaDeBizcochuelos bizcochueloTres) {
        this.id_detalle_torta = id_detalle_torta;
        this.pisos = pisos;
        this.estructura = estructura;
        this.sabores = new ArrayList<>(sabores);
        this.tamaño = tamaño;
        this.tamañoDos = tamañoDos;
        this.tamañoTres = tamañoTres;
        this.bizcochuelo = bizcochuelo;
        this.bizcochueloDos = bizcochueloDos;
        this.bizcochueloTres = bizcochueloTres;
    }

    public DetalleDeTorta toDomainModel() {
        return new DetalleDeTorta(id_detalle_torta, pisos, estructura,
                sabores.stream().map(EntidadListaDeSabores::toDomainModel).collect(Collectors.toList()),
                tamañoADominio(tamaño), tamañoADominio(tamañoDos), tamañoADominio(tamañoTres),
                bizcochueloADominio(bizcochuelo), bizcochueloADominio(bizcochueloDos),
                bizcochueloADominio(bizcochueloTres));
    }

    public EntidadDetalleDeTorta fromDomainModel(DetalleDeTorta detalleDeTorta) {
        List<EntidadListaDeSabores> sabores = detalleDeTorta.getSabores() == null
                ? new ArrayList<>()
                : new EntidadListaDeSabores().lista(detalleDeTorta.getSabores());

        return new EntidadDetalleDeTorta(detalleDeTorta.getIdDetalleTorta(), detalleDeTorta.getPisos(),
                detalleDeTorta.getEstructura(), sabores,
                tamañoAEntidad(detalleDeTorta.getTamaño()), tamañoAEntidad(detalleDeTorta.getTamañoDos()),
                tamañoAEntidad(detalleDeTorta.getTamañoTres()),
                bizcochueloAEntidad(detalleDeTorta.getBizcochuelo()),
                bizcochueloAEntidad(detalleDeTorta.getBizcochueloDos()),
                bizcochueloAEntidad(detalleDeTorta.getBizcochueloTres()));
    }

    private static EntidadListaDeTamaños tamañoAEntidad(ListaDeTamaños t) {
        return (t == null || t.getIdLista_tamaño() == null) ? null : new EntidadListaDeTamaños().fromDomainModel(t);
    }

    private static EntidadListaDeBizcochuelos bizcochueloAEntidad(ListaDeBizcochuelos b) {
        return (b == null || b.getIdLista_bizcochuelo() == null) ? null
                : new EntidadListaDeBizcochuelos().fromDomainModel(b);
    }

    private static ListaDeTamaños tamañoADominio(EntidadListaDeTamaños t) {
        return t == null ? null : t.toDomainModel();
    }

    private static ListaDeBizcochuelos bizcochueloADominio(EntidadListaDeBizcochuelos b) {
        return b == null ? null : b.toDomainModel();
    }

    public Long getIdDetalleTorta() {
        return this.id_detalle_torta;
    }

    public void setIdDetalleTorta(Long id_detalle_torta) {
        this.id_detalle_torta = id_detalle_torta;
    }

    public Long getPisos() {
        return this.pisos;
    }

    public void setPisos(Long pisos) {
        this.pisos = pisos;
    }

    public EntidadListaDeTamaños getTamaño() {
        return this.tamaño;
    }

    public void setTamaño(EntidadListaDeTamaños tamaño) {
        this.tamaño = tamaño;
    }

    public EntidadListaDeTamaños getTamañoDos() {
        return this.tamañoDos;
    }

    public void setTamañoDos(EntidadListaDeTamaños tamañoDos) {
        this.tamañoDos = tamañoDos;
    }

    public EntidadListaDeTamaños getTamañoTres() {
        return this.tamañoTres;
    }

    public void setTamañoTres(EntidadListaDeTamaños tamañoTres) {
        this.tamañoTres = tamañoTres;
    }

    public EntidadListaDeBizcochuelos getBizcochuelo() {
        return this.bizcochuelo;
    }

    public void setBizcochuelo(EntidadListaDeBizcochuelos bizcochuelo) {
        this.bizcochuelo = bizcochuelo;
    }

    public EntidadListaDeBizcochuelos getBizcochueloDos() {
        return this.bizcochueloDos;
    }

    public void setBizcochueloDos(EntidadListaDeBizcochuelos bizcochueloDos) {
        this.bizcochueloDos = bizcochueloDos;
    }

    public EntidadListaDeBizcochuelos getBizcochueloTres() {
        return this.bizcochueloTres;
    }

    public void setBizcochueloTres(EntidadListaDeBizcochuelos bizcochueloTres) {
        this.bizcochueloTres = bizcochueloTres;
    }

    public Long getId_detalle_torta() {
        return this.id_detalle_torta;
    }

    public void setId_detalle_torta(Long id_detalle_torta) {
        this.id_detalle_torta = id_detalle_torta;
    }

    public List<EntidadListaDeSabores> getSabores() {
        return this.sabores;
    }

    public void setSabores(List<EntidadListaDeSabores> sabores) {
        this.sabores = sabores;
    }

    public EntidadTipoDeProducto getTipoDeProductoproducto() {
        return this.tipoDeProductoproducto;
    }

    public void setTipoDeProductoproducto(EntidadTipoDeProducto tipoDeProductoproducto) {
        this.tipoDeProductoproducto = tipoDeProductoproducto;
    }

    public Boolean isEstructura() {
        return this.estructura;
    }

    public Boolean getEstructura() {
        return this.estructura;
    }

    public void setEstructura(Boolean estructura) {
        this.estructura = estructura;
    }

    @Override
    public String toString() {
        return "{" +
                " id_detalle_torta='" + getIdDetalleTorta() + "'" +
                ", pisos='" + getPisos() + "'" +
                ", tamaño='" + (tamaño == null ? null : tamaño.getIdLista_tamaño()) + "'" +
                ", tamañoDos='" + (tamañoDos == null ? null : tamañoDos.getIdLista_tamaño()) + "'" +
                ", tamañoTres='" + (tamañoTres == null ? null : tamañoTres.getIdLista_tamaño()) + "'" +
                ", bizcochuelo='" + (bizcochuelo == null ? null : bizcochuelo.getIdLista_bizcochuelo()) + "'" +
                ", bizcochueloDos='" + (bizcochueloDos == null ? null : bizcochueloDos.getIdLista_bizcochuelo()) + "'" +
                ", bizcochueloTres='" + (bizcochueloTres == null ? null : bizcochueloTres.getIdLista_bizcochuelo()) + "'" +
                "}";
    }

}
