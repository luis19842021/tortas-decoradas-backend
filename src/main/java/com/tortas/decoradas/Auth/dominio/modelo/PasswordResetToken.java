package com.tortas.decoradas.Auth.dominio.modelo;

import java.util.Date;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public class PasswordResetToken {
    private Long id;
    private String token;
    private Date expirationTime;
    private Usuario usuario;

    public PasswordResetToken(Long id, String token, Date expirationTime, Usuario usuario) {
        this.id = id;
        this.token = token;
        this.expirationTime = expirationTime;
        this.usuario = usuario;
    }

    public Long getId() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }

    public Date getExpirationTime() {
        return this.expirationTime;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
}
