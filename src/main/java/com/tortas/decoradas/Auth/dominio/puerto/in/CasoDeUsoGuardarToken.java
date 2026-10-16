package com.tortas.decoradas.Auth.dominio.puerto.in;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface CasoDeUsoGuardarToken {
    void saveUserVerificationToken(Usuario theUser, String verificationToken);
}
