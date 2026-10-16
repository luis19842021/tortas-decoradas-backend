package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoReenviarVerificacion;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoReenviarVerificacionImpl implements CasoDeUsoReenviarVerificacion {

    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoReenviarVerificacionImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public void reenviarTokenVerificacion(String email) {
        puertoRepositorioAuth.resendVerificationToken(email);
    }

}
