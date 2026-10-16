package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoGuardarToken;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public class CasoDeUsoGuardarTokenImpl implements CasoDeUsoGuardarToken {
    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoGuardarTokenImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

     @Override
    public void saveUserVerificationToken(Usuario theUser, String verificationToken) {
      puertoRepositorioAuth.saveUserVerificationToken(theUser, verificationToken);
    }

}
