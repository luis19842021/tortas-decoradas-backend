package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoSolicitarRecuperacion;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoSolicitarRecuperacionImpl implements CasoDeUsoSolicitarRecuperacion {

    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoSolicitarRecuperacionImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public void solicitarRecuperacion(String email) {
        puertoRepositorioAuth.solicitarRecuperacionContrasenia(email);
    }
}
