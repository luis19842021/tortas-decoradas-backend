package com.tortas.decoradas.Auth.infraestructura.entiAuth;

import java.util.Calendar;
import java.util.Date;

import com.tortas.decoradas.Auth.dominio.modelo.PasswordResetToken;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_reset_token")
public class EntidadPasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(nullable = false)
    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private EntidadUsuario usuario;

    private static final int EXPIRATION_TIME_MINUTOS = 30;

    public EntidadPasswordResetToken() {
    }

    public EntidadPasswordResetToken(String token, EntidadUsuario usuario) {
        this.token = token;
        this.expirationTime = calcularExpiracion();
        this.usuario = usuario;
    }

    private Date calcularExpiracion() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME_MINUTOS);
        return calendar.getTime();
    }

    public void actualizarToken(String nuevoToken) {
        this.token = nuevoToken;
        this.expirationTime = calcularExpiracion();
    }

    public PasswordResetToken toDomainModel() {
        return new PasswordResetToken(id, token, expirationTime, usuario.toDomainModel());
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public EntidadUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(EntidadUsuario usuario) {
        this.usuario = usuario;
    }
}
