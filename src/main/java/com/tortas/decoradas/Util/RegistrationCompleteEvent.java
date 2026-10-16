package com.tortas.decoradas.Util;

import org.springframework.context.ApplicationEvent;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Usuario user;
    private String applicationUrl;
    private String verificationToken;

    public RegistrationCompleteEvent(Usuario user, String applicationUrl, String verificationToken) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.verificationToken = verificationToken;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
