package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoLogin;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoLoginImpl implements CasoDeUsoLogin {

 private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoLoginImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public String login(String email, String password) {
        return puertoRepositorioAuth.login(email, password);
    }

}
