package com.tortas.decoradas.pedido.dominio.modelo;

public class ItemPedido {

    private Long idItemPedido;
    private Long idProducto;
    private String descripcion;
    private Long precioUnitario;
    private Integer cantidad;
    private String imagenUrl;

    public ItemPedido(Long idItemPedido, Long idProducto, String descripcion, Long precioUnitario, Integer cantidad) {
        this.idItemPedido = idItemPedido;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public Long getIdItemPedido() {
        return this.idItemPedido;
    }

    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Long getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

    public String getImagenUrl() {
        return this.imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
