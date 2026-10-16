package com.tortas.decoradas.Auth.dominio.puerto.out;

import java.util.Optional;

import com.tortas.decoradas.Auth.dominio.modelo.Tokentemp;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface PuertoRepositorioAuth {
    Optional<Usuario> findByEmail(String email);

    void saveUserVerificationToken(Usuario theUser, String verificationToken);

    String login(String email, String password);

    String signUp(String nombre, String apellido, String contraseña, String email, String telefono);

    String validateToken(String theToken);

    String verifyToken(String token);

    Tokentemp buscarToken(String token);

    void resendVerificationToken(String email);

    void solicitarRecuperacionContrasenia(String email);

    String validarTokenRecuperacion(String token);

    void restablecerContrasenia(String token, String nuevaContrasenia);
}
