package com.tortas.decoradas.producto.dominio.modelo;

import java.util.ArrayList;
import java.util.List;

public class DetalleDeTorta {

    private Long idDetalleTorta;
    private Long pisos;
    private Boolean estructura;

    private List<ListaDeSabores> sabores;

    private ListaDeTamaños tamaño;
    private ListaDeTamaños tamañoDos;
    private ListaDeTamaños tamañoTres;

    private ListaDeBizcochuelos bizcochuelo;
    private ListaDeBizcochuelos bizcochueloDos;
    private ListaDeBizcochuelos bizcochueloTres;

    public DetalleDeTorta() {
    }

    public DetalleDeTorta(Long idDetalleTorta, Long pisos, Boolean estructura, List<ListaDeSabores> sabores,
            ListaDeTamaños tamaño, ListaDeTamaños tamañoDos, ListaDeTamaños tamañoTres,
            ListaDeBizcochuelos bizcochuelo, ListaDeBizcochuelos bizcochueloDos,
            ListaDeBizcochuelos bizcochueloTres) {
        this.idDetalleTorta = idDetalleTorta;
        this.pisos = pisos;
        this.estructura = estructura;
        this.sabores = sabores == null ? new ArrayList<>() : new ArrayList<>(sabores);
        this.tamaño = tamaño;
        this.tamañoDos = tamañoDos;
        this.tamañoTres = tamañoTres;
        this.bizcochuelo = bizcochuelo;
        this.bizcochueloDos = bizcochueloDos;
        this.bizcochueloTres = bizcochueloTres;
    }

    public Long getIdDetalleTorta() {
        return this.idDetalleTorta;
    }

    public void setIdDetalleTorta(Long idDetalleTorta) {
        this.idDetalleTorta = idDetalleTorta;
    }

    public Long getPisos() {
        return this.pisos;
    }

    public void setPisos(Long pisos) {
        this.pisos = pisos;
    }

    public Boolean getEstructura() {
        return this.estructura;
    }

    public void setEstructura(Boolean estructura) {
        this.estructura = estructura;
    }

    public List<ListaDeSabores> getSabores() {
        return this.sabores;
    }

    public void setSabores(List<ListaDeSabores> sabores) {
        this.sabores = sabores;
    }

    public ListaDeTamaños getTamaño() {
        return this.tamaño;
    }

    public void setTamaño(ListaDeTamaños tamaño) {
        this.tamaño = tamaño;
    }

    public ListaDeTamaños getTamañoDos() {
        return this.tamañoDos;
    }

    public void setTamañoDos(ListaDeTamaños tamañoDos) {
        this.tamañoDos = tamañoDos;
    }

    public ListaDeTamaños getTamañoTres() {
        return this.tamañoTres;
    }

    public void setTamañoTres(ListaDeTamaños tamañoTres) {
        this.tamañoTres = tamañoTres;
    }

    public ListaDeBizcochuelos getBizcochuelo() {
        return this.bizcochuelo;
    }

    public void setBizcochuelo(ListaDeBizcochuelos bizcochuelo) {
        this.bizcochuelo = bizcochuelo;
    }

    public ListaDeBizcochuelos getBizcochueloDos() {
        return this.bizcochueloDos;
    }

    public void setBizcochueloDos(ListaDeBizcochuelos bizcochueloDos) {
        this.bizcochueloDos = bizcochueloDos;
    }

    public ListaDeBizcochuelos getBizcochueloTres() {
        return this.bizcochueloTres;
    }

    public void setBizcochueloTres(ListaDeBizcochuelos bizcochueloTres) {
        this.bizcochueloTres = bizcochueloTres;
    }

    @Override
    public String toString() {
        return "{" +
                " idDetalleTorta='" + getIdDetalleTorta() + "'" +
                ", pisos='" + getPisos() + "'" +
                ", tamaño='" + idTamaño(getTamaño()) + "'" +
                ", tamañoDos='" + idTamaño(getTamañoDos()) + "'" +
                ", tamañoTres='" + idTamaño(getTamañoTres()) + "'" +
                ", bizcochuelo='" + idBizcochuelo(getBizcochuelo()) + "'" +
                ", bizcochueloDos='" + idBizcochuelo(getBizcochueloDos()) + "'" +
                ", bizcochueloTres='" + idBizcochuelo(getBizcochueloTres()) + "'" +
                "}";
    }

    private static Long idTamaño(ListaDeTamaños t) {
        return t == null ? null : t.getIdLista_tamaño();
    }

    private static Long idBizcochuelo(ListaDeBizcochuelos b) {
        return b == null ? null : b.getIdLista_bizcochuelo();
    }

}
