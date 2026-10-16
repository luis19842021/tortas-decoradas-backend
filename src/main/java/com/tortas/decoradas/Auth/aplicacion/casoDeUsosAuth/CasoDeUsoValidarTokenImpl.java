package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoValidarToken;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoValidarTokenImpl implements CasoDeUsoValidarToken {
    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoValidarTokenImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public String validateToken(String theToken) {
        return puertoRepositorioAuth.validateToken(theToken);
    }

}
