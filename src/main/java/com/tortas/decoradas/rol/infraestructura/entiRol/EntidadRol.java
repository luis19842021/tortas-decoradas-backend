package com.tortas.decoradas.rol.infraestructura.entiRol;

import com.tortas.decoradas.rol.dominio.modelo.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class EntidadRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRol;

    @Column(unique = true)
    private String nombre;

    public EntidadRol() {
    }

    public EntidadRol(Long idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public static EntidadRol fromDomainModel(Rol rol) {
        return new EntidadRol(rol.getIdRol(), rol.getNombre());
    }

    public Rol toDomainModel() {
        return new Rol(this.idRol, this.nombre);
    }

    public Long getIdRol() {
        return this.idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
