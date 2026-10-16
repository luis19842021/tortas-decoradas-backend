package com.tortas.decoradas.producto.infraestructura.entiProdu;

import java.util.HashSet;
import java.util.Set;
import com.tortas.decoradas.producto.dominio.modelo.Alergeno;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lista_sabores")
public class EntidadListaDeSabores {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "sabor_alergeno", joinColumns = @JoinColumn(name = "id_lista_sabor"))
    @Enumerated(EnumType.STRING)
    @Column(name = "alergeno", length = 20)
    private Set<Alergeno> alergenos = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLista_sabor;
    private String sabor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria_sabor")
    private EntidadCategoriaSabor categoria;

    @ManyToMany(mappedBy = "sabores")
    private List<EntidadDetalleDeTorta> detalleDeTorta;

    public EntidadListaDeSabores() {
    }

    public EntidadListaDeSabores(Long idLista_sabor, String sabor, EntidadCategoriaSabor categoria) {
        this.idLista_sabor = idLista_sabor;
        this.sabor = sabor;
        this.categoria = categoria;
    }

    public ListaDeSabores toDomainModel() {
        CategoriaSabor categoriaDomain = categoria == null ? null : categoria.toDomainModel();
        ListaDeSabores dominio = new ListaDeSabores(idLista_sabor, sabor, categoriaDomain);
        dominio.setAlergenos(new HashSet<>(alergenos));
        return dominio;
    }

    public EntidadListaDeSabores fromDomainModel(ListaDeSabores listaDeSabores) {
        EntidadCategoriaSabor categoriaEntidad = listaDeSabores.getCategoria() == null
                || listaDeSabores.getCategoria().getIdCategoriaSabor() == null
                        ? null
                        : new EntidadCategoriaSabor().fromDomainModel(listaDeSabores.getCategoria());
        EntidadListaDeSabores entidad = new EntidadListaDeSabores(listaDeSabores.getIdLista_sabor(), listaDeSabores.getSabor(),
                categoriaEntidad);
        entidad.alergenos = listaDeSabores.getAlergenos() == null ? new HashSet<>() : new HashSet<>(listaDeSabores.getAlergenos());
        return entidad;
    }

    public List<EntidadListaDeSabores> lista(List<ListaDeSabores> lista){
     List<EntidadListaDeSabores> nuevaLista=new ArrayList<EntidadListaDeSabores>();
     for (ListaDeSabores ListaDeSabores : lista) {
        nuevaLista.add(new EntidadListaDeSabores().fromDomainModel(ListaDeSabores));
     }

        return nuevaLista ;
    }

    public Long getIdLista_sabor() {
        return this.idLista_sabor;
    }

    public void setIdLista_sabor(Long idLista_sabor) {
        this.idLista_sabor = idLista_sabor;
    }

    public String getSabor() {
        return this.sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public List<EntidadDetalleDeTorta> getDetalleDeTorta() {
        return this.detalleDeTorta;
    }

    public void setDetalleDeTorta(List<EntidadDetalleDeTorta> detalleDeTorta) {
        this.detalleDeTorta = detalleDeTorta;
    }

    public EntidadCategoriaSabor getCategoria() {
        return this.categoria;
    }

    public void setCategoria(EntidadCategoriaSabor categoria) {
        this.categoria = categoria;
    }

    public Set<Alergeno> getAlergenos() {
        return this.alergenos;
    }

    public void setAlergenos(Set<Alergeno> alergenos) {
        this.alergenos = alergenos;
    }

}
