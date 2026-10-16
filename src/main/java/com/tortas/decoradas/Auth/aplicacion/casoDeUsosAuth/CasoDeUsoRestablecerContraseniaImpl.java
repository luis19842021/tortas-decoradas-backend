package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoRestablecerContrasenia;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

public class CasoDeUsoRestablecerContraseniaImpl implements CasoDeUsoRestablecerContrasenia {

    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoRestablecerContraseniaImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public String validarTokenRecuperacion(String token) {
        return puertoRepositorioAuth.validarTokenRecuperacion(token);
    }

    @Override
    public void restablecerContrasenia(String token, String nuevaContrasenia) {
        puertoRepositorioAuth.restablecerContrasenia(token, nuevaContrasenia);
    }
}
