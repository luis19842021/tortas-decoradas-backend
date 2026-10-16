package com.tortas.decoradas.pedido.dominio.modelo;

import java.time.LocalDate;
import java.util.List;

public class CrearPedidoRequestDto {

    private LocalDate fechaDeEntrega;
    private List<ItemPedidoRequestDto> items;
    private String direccionEnvio;
    private Long codigoPostalEnvio;
    private boolean aceptaTerminos;
    private String versionTerminos;

    public CrearPedidoRequestDto() {
    }

    public LocalDate getFechaDeEntrega() {
        return this.fechaDeEntrega;
    }

    public void setFechaDeEntrega(LocalDate fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public List<ItemPedidoRequestDto> getItems() {
        return this.items;
    }

    public void setItems(List<ItemPedidoRequestDto> items) {
        this.items = items;
    }

    public String getDireccionEnvio() {
        return this.direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Long getCodigoPostalEnvio() {
        return this.codigoPostalEnvio;
    }

    public void setCodigoPostalEnvio(Long codigoPostalEnvio) {
        this.codigoPostalEnvio = codigoPostalEnvio;
    }

    public boolean isAceptaTerminos() {
        return this.aceptaTerminos;
    }

    public void setAceptaTerminos(boolean aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }

    public String getVersionTerminos() {
        return this.versionTerminos;
    }

    public void setVersionTerminos(String versionTerminos) {
        this.versionTerminos = versionTerminos;
    }
}
