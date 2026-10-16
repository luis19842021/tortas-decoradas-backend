package com.tortas.decoradas.pedido.dominio.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private Long idpedido;
    private LocalDate fecha_de_pedido;
    private Estado estado;
    private EstadoPreparacion estadoPreparacion;
    private Long id_usuario;
    private List<ItemPedido> items;
    private boolean reprogramado;
    private Long montoADevolver;
    private Integer porcentajeDevuelto;

    private String direccionEnvio;
    private Long codigoPostalEnvio;
    private Long costoEnvio;
    private LocalDateTime terminosAceptadosEn;
    private String versionTerminos;

    public Pedido(Long idpedido, LocalDate fecha_de_pedido, Estado estado, Long id_usuario, List<ItemPedido> items) {
        this(idpedido, fecha_de_pedido, estado, null, id_usuario, items, false, null, null);
    }

    public Pedido(Long idpedido, LocalDate fecha_de_pedido, Estado estado, EstadoPreparacion estadoPreparacion,
            Long id_usuario, List<ItemPedido> items) {
        this(idpedido, fecha_de_pedido, estado, estadoPreparacion, id_usuario, items, false, null, null);
    }

    public Pedido(Long idpedido, LocalDate fecha_de_pedido, Estado estado, EstadoPreparacion estadoPreparacion,
            Long id_usuario, List<ItemPedido> items, boolean reprogramado) {
        this(idpedido, fecha_de_pedido, estado, estadoPreparacion, id_usuario, items, reprogramado, null, null);
    }

    public Pedido(Long idpedido, LocalDate fecha_de_pedido, Estado estado, EstadoPreparacion estadoPreparacion,
            Long id_usuario, List<ItemPedido> items, boolean reprogramado, Long montoADevolver,
            Integer porcentajeDevuelto) {
        this.idpedido = idpedido;
        this.fecha_de_pedido = fecha_de_pedido;
        this.estado = estado;
        this.estadoPreparacion = estadoPreparacion;
        this.id_usuario = id_usuario;
        this.items = items;
        this.reprogramado = reprogramado;
        this.montoADevolver = montoADevolver;
        this.porcentajeDevuelto = porcentajeDevuelto;
    }

    public List<ItemPedido> getItems() {
        return this.items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public Long getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getIdpedido() {
        return this.idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public LocalDate getFecha_de_pedido() {
        return this.fecha_de_pedido;
    }

    public void setFecha_de_pedido(LocalDate fecha_de_pedido) {
        this.fecha_de_pedido = fecha_de_pedido;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstadoPreparacion getEstadoPreparacion() {
        return this.estadoPreparacion;
    }

    public void setEstadoPreparacion(EstadoPreparacion estadoPreparacion) {
        this.estadoPreparacion = estadoPreparacion;
    }

    public boolean isReprogramado() {
        return this.reprogramado;
    }

    public void setReprogramado(boolean reprogramado) {
        this.reprogramado = reprogramado;
    }

    public Long getMontoADevolver() {
        return this.montoADevolver;
    }

    public void setMontoADevolver(Long montoADevolver) {
        this.montoADevolver = montoADevolver;
    }

    public Integer getPorcentajeDevuelto() {
        return this.porcentajeDevuelto;
    }

    public void setPorcentajeDevuelto(Integer porcentajeDevuelto) {
        this.porcentajeDevuelto = porcentajeDevuelto;
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

    public Long getCostoEnvio() {
        return this.costoEnvio;
    }

    public void setCostoEnvio(Long costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public LocalDateTime getTerminosAceptadosEn() {
        return this.terminosAceptadosEn;
    }

    public void setTerminosAceptadosEn(LocalDateTime terminosAceptadosEn) {
        this.terminosAceptadosEn = terminosAceptadosEn;
    }

    public String getVersionTerminos() {
        return this.versionTerminos;
    }

    public void setVersionTerminos(String versionTerminos) {
        this.versionTerminos = versionTerminos;
    }

}
