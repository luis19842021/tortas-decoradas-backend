package com.tortas.decoradas.Auth.dominio.modelo;

import java.util.Date;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public class Tokentemp {
    private Long id;
    private String token;
    private Date expirationTime;
    private Usuario usuario;

    public Tokentemp(Long id, String token, Date expirationTime, Usuario usuario) {
        this.id = id;
        this.token = token;
        this.expirationTime = expirationTime;
        this.usuario = usuario;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
