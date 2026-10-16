package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.modelo.Tokentemp;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoBuscarToken;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoBuscarTokenImpl implements CasoDeUsoBuscarToken {
    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoBuscarTokenImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public Tokentemp buscarToken(String token) {
        return puertoRepositorioAuth.buscarToken(token);
    }

}
