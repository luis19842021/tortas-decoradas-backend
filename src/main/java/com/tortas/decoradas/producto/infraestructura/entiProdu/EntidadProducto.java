package com.tortas.decoradas.producto.infraestructura.entiProdu;

import java.util.HashSet;
import java.util.Set;

import com.tortas.decoradas.producto.dominio.modelo.Alergeno;
import com.tortas.decoradas.producto.dominio.modelo.Categoria;
import com.tortas.decoradas.producto.dominio.modelo.CategoriaEspecial;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.modelo.SubCategoria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")

public class EntidadProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombre_producto;
    private Long precio;
    private String url_image;
    private String url_image2;
    private String url_image3;
    private String url_image4;

    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(name = "subcategoria")
    @Enumerated(EnumType.STRING)
    private SubCategoria subcategoria;

    @Column(name = "categoesp")
    @Enumerated(EnumType.STRING)
    private CategoriaEspecial categoesp;

    @Column(name = "suspendido", nullable = false)
    private boolean suspendido = false;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Tipo_de_producto", referencedColumnName = "id_Tipo_de_producto")
    private EntidadTipoDeProducto tipoProducto;

    @Column(name = "ingredientes", columnDefinition = "TEXT")
    private String ingredientes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "producto_alergeno", joinColumns = @JoinColumn(name = "id_producto"))
    @Enumerated(EnumType.STRING)
    @Column(name = "alergeno", length = 20)
    private Set<Alergeno> alergenos = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "producto_traza", joinColumns = @JoinColumn(name = "id_producto"))
    @Enumerated(EnumType.STRING)
    @Column(name = "alergeno", length = 20)
    private Set<Alergeno> trazas = new HashSet<>();

    public EntidadProducto() {
    }

    public EntidadProducto(Long idProducto, String nombre_producto, Long precio, String url_image, String url_image2,
            String url_image3, String url_image4, String descripcion,
            Categoria categoria,
            SubCategoria subcategoria,
            CategoriaEspecial categoesp,
            EntidadTipoDeProducto tipoProducto,
            boolean suspendido) {
        this.idProducto = idProducto;
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
        this.tipoProducto = tipoProducto;
        this.suspendido = suspendido;
    }

    public Producto toDomainModel() {

        Producto producto = new Producto(
                idProducto, nombre_producto, precio, url_image, url_image2, url_image3, url_image4, descripcion,
                categoria, subcategoria, categoesp,
                tipoProducto.toDomainModel(), suspendido);
        producto.setIngredientes(ingredientes);
        producto.setAlergenos(new HashSet<>(alergenos));
        producto.setTrazas(new HashSet<>(trazas));
        return producto;

    }

    public static EntidadProducto fromDomainModel(Producto producto) {

        EntidadProducto entidad = new EntidadProducto(producto.getIdProducto(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getUrl_image(),
                producto.getUrl_image2(),
                producto.getUrl_image3(),
                producto.getUrl_image4(),
                producto.getDescripcion(),
                producto.getCategoria(),
                producto.getSubcategoria(),
                producto.getCategoesp(),
                new EntidadTipoDeProducto().fromDomainModel(producto.getTipoDeProducto()),
                producto.isSuspendido());
        entidad.ingredientes = producto.getIngredientes();
        entidad.alergenos = producto.getAlergenos() == null ? new HashSet<>() : new HashSet<>(producto.getAlergenos());
        entidad.trazas = producto.getTrazas() == null ? new HashSet<>() : new HashSet<>(producto.getTrazas());
        return entidad;
    }

    public String getNombre() {
        return this.nombre_producto;
    }

    public void setNombre(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Long getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

}
