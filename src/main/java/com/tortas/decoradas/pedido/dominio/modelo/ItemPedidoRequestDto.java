package com.tortas.decoradas.pedido.dominio.modelo;

public class ItemPedidoRequestDto {

    private Long idProducto;
    private String descripcion;
    private Long precioUnitario;
    private Integer cantidad;

    public ItemPedidoRequestDto() {
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
}
