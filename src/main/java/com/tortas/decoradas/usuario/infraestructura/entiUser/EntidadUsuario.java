package com.tortas.decoradas.usuario.infraestructura.entiUser;

import java.util.ArrayList;
import java.util.List;

import com.tortas.decoradas.rol.dominio.modelo.Rol;
import com.tortas.decoradas.rol.infraestructura.entiRol.EntidadRol;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")

public class EntidadUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;
    private String contraseña;
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<EntidadRol> roles = new ArrayList<>();

    @Column(name = "enabled", nullable = false)
    private boolean enabled = false;

    @Column(name = "suspendida", nullable = false)
    private boolean suspendida = false;

    public EntidadUsuario() {

    }

    public EntidadUsuario(Long user_id, String nombre, String apellido, String email, String contraseña,
            String telefono, List<EntidadRol> roles) {
        this(user_id, nombre, apellido, email, contraseña, telefono, roles, false, false);
    }

    public EntidadUsuario(Long user_id, String nombre, String apellido, String email, String contraseña,
            String telefono, List<EntidadRol> roles, boolean enabled, boolean suspendida) {
        this.user_id = user_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.roles = roles;
        this.enabled = enabled;
        this.suspendida = suspendida;
    }

    public static EntidadUsuario fromDomainModel(Usuario usuario) {
        return new EntidadUsuario(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(),
                usuario.getContraseña(), usuario.getTelefono(),
                usuario.getRoles().stream().map(rol -> new EntidadRol(rol.getIdRol(), rol.getNombre())).toList(),
                usuario.isEmailVerificado(), usuario.isSuspendida());
    }

    public Usuario toDomainModel() {
        return new Usuario(
                user_id, nombre, apellido, email, contraseña, telefono,
                roles.stream().map(rol -> new Rol(rol.getIdRol(), rol.getNombre())).toList(),
                enabled, suspendida);
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSuspendida() {
        return this.suspendida;
    }

    public void setSuspendida(boolean suspendida) {
        this.suspendida = suspendida;
    }

    public List<EntidadRol> getRoles() {
        return this.roles;
    }

    public void setRoles(List<EntidadRol> roles) {
        this.roles = roles;
    }

}
