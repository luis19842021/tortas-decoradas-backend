package com.tortas.decoradas.pedido.insfraestructura.entiPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.EstadoPreparacion;
import com.tortas.decoradas.pedido.dominio.modelo.ItemPedido;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;

@Entity
@Table(name = "pedido")
public class EntidadPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpedido;
    private LocalDate fecha_de_pedido;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private EstadoPreparacion estadoPreparacion;

    private boolean reprogramado;

    private Long montoADevolver;
    private Integer porcentajeDevuelto;

    private String direccionEnvio;
    private Long codigoPostalEnvio;
    private Long costoEnvio;
    private LocalDateTime terminosAceptadosEn;
    private String versionTerminos;

    private LocalDateTime fechaCreacion;

    @PrePersist
    void marcarFechaCreacion() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private EntidadUsuario id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EntidadItemPedido> items = new ArrayList<>();

    public EntidadPedido() {

    }

    public EntidadPedido(Long idpedido, LocalDate fecha_de_pedido,Estado estado  ) {
        this.idpedido = idpedido;
        this.fecha_de_pedido = fecha_de_pedido;
        this.estado = estado;

    }

    public static EntidadPedido fromDomainModel(Pedido pedido ) {
        EntidadPedido entidad = new EntidadPedido(pedido.getIdpedido(), pedido.getFecha_de_pedido(),
                pedido.getEstado());
        entidad.setEstadoPreparacion(pedido.getEstadoPreparacion());
        entidad.setReprogramado(pedido.isReprogramado());
        entidad.setMontoADevolver(pedido.getMontoADevolver());
        entidad.setPorcentajeDevuelto(pedido.getPorcentajeDevuelto());
        entidad.setDireccionEnvio(pedido.getDireccionEnvio());
        entidad.setCodigoPostalEnvio(pedido.getCodigoPostalEnvio());
        entidad.setCostoEnvio(pedido.getCostoEnvio());
        entidad.setTerminosAceptadosEn(pedido.getTerminosAceptadosEn());
        entidad.setVersionTerminos(pedido.getVersionTerminos());
        return entidad;
    }

    public Pedido toDomainModel() {
        List<ItemPedido> itemsDominio = items == null
                ? new ArrayList<>()
                : items.stream().map(EntidadItemPedido::toDomainModel).collect(Collectors.toList());

        Pedido pedido = new Pedido(
                idpedido, fecha_de_pedido, estado, estadoPreparacion, id.getUser_id(), itemsDominio, reprogramado,
                montoADevolver, porcentajeDevuelto);
        pedido.setDireccionEnvio(direccionEnvio);
        pedido.setCodigoPostalEnvio(codigoPostalEnvio);
        pedido.setCostoEnvio(costoEnvio);
        pedido.setTerminosAceptadosEn(terminosAceptadosEn);
        pedido.setVersionTerminos(versionTerminos);
        return pedido;
    }

    public Long getIdpedido() {
        return this.idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public EntidadUsuario getId() {
        return this.id;
    }

    public void setId(EntidadUsuario id) {
        this.id = id;
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

    public List<EntidadItemPedido> getItems() {
        return this.items;
    }

    public void setItems(List<EntidadItemPedido> items) {
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
