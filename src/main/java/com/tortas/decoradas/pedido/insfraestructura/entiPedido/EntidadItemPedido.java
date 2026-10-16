package com.tortas.decoradas.pedido.insfraestructura.entiPedido;

import com.tortas.decoradas.pedido.dominio.modelo.ItemPedido;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadProducto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class EntidadItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private EntidadPedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private EntidadProducto producto;

    @Column(length = 1000)
    private String descripcion;

    private Long precioUnitario;

    private Integer cantidad;

    public EntidadItemPedido() {
    }

    public EntidadItemPedido(Long idItemPedido, String descripcion, Long precioUnitario, Integer cantidad) {
        this.idItemPedido = idItemPedido;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public static EntidadItemPedido fromDomainModel(ItemPedido itemPedido) {
        return new EntidadItemPedido(
                itemPedido.getIdItemPedido(),
                itemPedido.getDescripcion(),
                itemPedido.getPrecioUnitario(),
                itemPedido.getCantidad());
    }

    public ItemPedido toDomainModel() {
        return new ItemPedido(
                idItemPedido,
                producto != null ? producto.getIdProducto() : null,
                descripcion,
                precioUnitario,
                cantidad);
    }

    public Long getIdItemPedido() {
        return this.idItemPedido;
    }

    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public EntidadPedido getPedido() {
        return this.pedido;
    }

    public void setPedido(EntidadPedido pedido) {
        this.pedido = pedido;
    }

    public EntidadProducto getProducto() {
        return this.producto;
    }

    public void setProducto(EntidadProducto producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(Long precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
