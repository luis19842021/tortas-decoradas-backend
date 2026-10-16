package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoVerifyToken;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoVerifyTokenImpl implements CasoDeUsoVerifyToken {

    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoVerifyTokenImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public String verifyToken(String token) {
        return puertoRepositorioAuth.verifyToken(token);
    }

}
