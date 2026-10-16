package com.tortas.decoradas.usuario.dominio.modelo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tortas.decoradas.rol.dominio.modelo.Rol;

public class Usuario {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String telefono;
    private List<Rol> roles=new ArrayList<>();
    private boolean emailVerificado;
    private boolean suspendida;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String email, String contraseña, String telefono, List<Rol> roles
            ) {
        this(id, nombre, apellido, email, contraseña, telefono, roles, false, false);
    }

    public Usuario(Long id, String nombre, String apellido, String email, String contraseña, String telefono, List<Rol> roles,
            boolean emailVerificado, boolean suspendida) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.roles = roles;
        this.emailVerificado = emailVerificado;
        this.suspendida = suspendida;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Rol> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public boolean isEmailVerificado() {
        return this.emailVerificado;
    }

    public void setEmailVerificado(boolean emailVerificado) {
        this.emailVerificado = emailVerificado;
    }

    public boolean isSuspendida() {
        return this.suspendida;
    }

    public void setSuspendida(boolean suspendida) {
        this.suspendida = suspendida;
    }

}
