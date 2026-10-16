package com.tortas.decoradas.Auth.infraestructura.entiAuth;

import java.util.Date;
import java.util.Calendar;

import com.tortas.decoradas.Auth.dominio.modelo.Tokentemp;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

import jakarta.persistence.CascadeType;
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
@Table(name = "token")
public class EntidadTokentemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

   @Column(nullable = false)
    private Date expirationTime;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private EntidadUsuario usuario;

    @Column(nullable = false)
    private int intentosReenvio = 0;

    private Date ventanaInicioReenvio;

     private static final int EXPIRATION_TIME = 60 * 24;

    public EntidadTokentemp() {
    }

    public EntidadTokentemp( String token, EntidadUsuario usuario) {
        this.token = token;
        this.expirationTime = calculateExpirationTime();
        this.usuario = usuario;
    }

     public EntidadTokentemp(String token) {
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

    private Date calculateExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return calendar.getTime();
    }

    public void actualizarToken(String nuevoToken) {
        this.token = nuevoToken;
        this.expirationTime = calculateExpirationTime();
    }

    public static EntidadTokentemp fromDomainModel(Tokentemp token) {
        return new EntidadTokentemp(token.getToken());

    }

    public Tokentemp toDomainModel() {
        return new Tokentemp(id, token, expirationTime, usuario.toDomainModel());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public EntidadUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(EntidadUsuario usuario) {
        this.usuario = usuario;
    }

    public int getIntentosReenvio() {
        return intentosReenvio;
    }

    public void setIntentosReenvio(int intentosReenvio) {
        this.intentosReenvio = intentosReenvio;
    }

    public Date getVentanaInicioReenvio() {
        return ventanaInicioReenvio;
    }

    public void setVentanaInicioReenvio(Date ventanaInicioReenvio) {
        this.ventanaInicioReenvio = ventanaInicioReenvio;
    }
}
