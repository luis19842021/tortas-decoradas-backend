package com.tortas.decoradas.producto.dominio.modelo;

import java.util.Set;

public class Producto {

    private Long IdProducto;
    private String nombre_producto;
    private Long precio;
    private String url_image;
    private String url_image2;
    private String url_image3;
    private String url_image4;
    private String descripcion;
    private Categoria categoria;
    private SubCategoria subcategoria;
    private CategoriaEspecial categoesp;

    private TipoDeProducto tipoDeProducto;

    private boolean suspendido;

    private String ingredientes;
    private Set<Alergeno> alergenos;
    private Set<Alergeno> trazas;

    public Producto() {
    }

    public Producto(Long IdProducto, String nombre_producto, Long precio, String url_image, String url_image2,
            String url_image3, String url_image4, String descripcion,
            Categoria categoria, SubCategoria subcategoria, CategoriaEspecial categoesp, TipoDeProducto tipoDeProducto) {
        this(IdProducto, nombre_producto, precio, url_image, url_image2, url_image3, url_image4, descripcion,
                categoria, subcategoria, categoesp, tipoDeProducto, false);
    }

    public Producto(Long IdProducto, String nombre_producto, Long precio, String url_image, String url_image2,
            String url_image3, String url_image4, String descripcion,
            Categoria categoria, SubCategoria subcategoria, CategoriaEspecial categoesp, TipoDeProducto tipoDeProducto,
            boolean suspendido) {
        this.IdProducto = IdProducto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.url_image = url_image;
        this.url_image2 = url_image2;
        this.url_image3 = url_image3;
        this.url_image4 = url_image4;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.categoesp = categoesp;
        this.tipoDeProducto = tipoDeProducto;
        this.suspendido = suspendido;

    }

    public TipoDeProducto getTipoDeProducto() {
        return this.tipoDeProducto;
    }

    public void setTipoDeProducto(TipoDeProducto tipoDeProducto) {
        this.tipoDeProducto = tipoDeProducto;
    }

    public Long getIdProducto() {
        return this.IdProducto;
    }

    public void setIdProducto(Long IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getUrl_image() {
        return this.url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getUrl_image2() {
        return this.url_image2;
    }

    public void setUrl_image2(String url_image2) {
        this.url_image2 = url_image2;
    }

    public String getUrl_image3() {
        return this.url_image3;
    }

    public void setUrl_image3(String url_image3) {
        this.url_image3 = url_image3;
    }

    public String getUrl_image4() {
        return this.url_image4;
    }

    public void setUrl_image4(String url_image4) {
        this.url_image4 = url_image4;
    }

    public String getNombre() {
        return this.nombre_producto;
    }

    public void setNombre(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaEspecial getCategoesp() {
        return this.categoesp;
    }

    public void setCategoesp(CategoriaEspecial categoesp) {
        this.categoesp = categoesp;
    }

    public String getNombre_producto() {
        return this.nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public SubCategoria getSubcategoria() {
        return this.subcategoria;
    }

    public void setSubcategoria(SubCategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public boolean isSuspendido() {
        return this.suspendido;
    }

    public void setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
    }

    @Override
    public String toString() {
        return "{" +
                " IdProducto='" + getIdProducto() + "'" +
                ", nombre_producto='" + getNombre() + "'" +
                ", precio='" + getPrecio() + "'" +
                ", url_image='" + getUrl_image() + "'" +
                ", descripcion='" + getDescripcion() + "'" +
                ", categoria='" + getCategoria() + "'" +
                ", categoria_especial='" + getCategoesp() + "'" +
                ", tipoDeProducto='" + getTipoDeProducto() + "'" +
                ", suspendido='" + isSuspendido() + "'" +
                "}";
    }

    public String getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Set<Alergeno> getAlergenos() {
        return this.alergenos;
    }

    public void setAlergenos(Set<Alergeno> alergenos) {
        this.alergenos = alergenos;
    }

    public Set<Alergeno> getTrazas() {
        return this.trazas;
    }

    public void setTrazas(Set<Alergeno> trazas) {
        this.trazas = trazas;
    }

}
