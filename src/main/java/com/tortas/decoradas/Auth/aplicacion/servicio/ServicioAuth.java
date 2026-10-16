package com.tortas.decoradas.Auth.aplicacion.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tortas.decoradas.Auth.dominio.modelo.Tokentemp;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoBuscarPorEmail;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoBuscarToken;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoGuardarToken;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoLogin;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoReenviarVerificacion;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoRestablecerContrasenia;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoSignUp;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoSolicitarRecuperacion;
import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoValidarToken;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

@Service
public class ServicioAuth
        implements CasoDeUsoSignUp, CasoDeUsoGuardarToken, CasoDeUsoBuscarToken, CasoDeUsoValidarToken, CasoDeUsoLogin,
        CasoDeUsoBuscarPorEmail, CasoDeUsoReenviarVerificacion, CasoDeUsoSolicitarRecuperacion, CasoDeUsoRestablecerContrasenia {

    private final CasoDeUsoSignUp casoDeUsoSignUp;
    private final CasoDeUsoGuardarToken casoDeUsoGuardarToken;
    private final CasoDeUsoBuscarToken casoDeUsoBuscarToken;
    private final CasoDeUsoValidarToken casoDeUsoValidarToken;
    private final CasoDeUsoLogin casoDeUsoLogin;
    private final CasoDeUsoBuscarPorEmail casoDeUsoBuscarPorEmail;
    private final CasoDeUsoReenviarVerificacion casoDeUsoReenviarVerificacion;
    private final CasoDeUsoSolicitarRecuperacion casoDeUsoSolicitarRecuperacion;
    private final CasoDeUsoRestablecerContrasenia casoDeUsoRestablecerContrasenia;

    public ServicioAuth(CasoDeUsoSignUp casoDeUsoSignUp,
            CasoDeUsoGuardarToken casoDeUsoGuardarToken,
            CasoDeUsoBuscarToken casoDeUsoBuscarToken,
            CasoDeUsoValidarToken casoDeUsoValidarToken,
            CasoDeUsoLogin casoDeUsoLogin,
            CasoDeUsoBuscarPorEmail casoDeUsoBuscarPorEmail,
            CasoDeUsoReenviarVerificacion casoDeUsoReenviarVerificacion,
            CasoDeUsoSolicitarRecuperacion casoDeUsoSolicitarRecuperacion,
            CasoDeUsoRestablecerContrasenia casoDeUsoRestablecerContrasenia
        ) {
        this.casoDeUsoSignUp = casoDeUsoSignUp;
        this.casoDeUsoGuardarToken = casoDeUsoGuardarToken;
        this.casoDeUsoBuscarToken = casoDeUsoBuscarToken;
        this.casoDeUsoValidarToken = casoDeUsoValidarToken;
        this.casoDeUsoLogin = casoDeUsoLogin;
        this.casoDeUsoBuscarPorEmail = casoDeUsoBuscarPorEmail;
        this.casoDeUsoReenviarVerificacion = casoDeUsoReenviarVerificacion;
        this.casoDeUsoSolicitarRecuperacion = casoDeUsoSolicitarRecuperacion;
        this.casoDeUsoRestablecerContrasenia = casoDeUsoRestablecerContrasenia;
    }

    @Override
    public String signUp(String nombre, String apellido, String contraseña, String email, String telefono) {

        return casoDeUsoSignUp.signUp(nombre, apellido, contraseña, email, telefono);
    }

    @Override
    public String login(String email, String contraseña) {
        return casoDeUsoLogin.login(email, contraseña);
    }

    @Override
    public void saveUserVerificationToken(Usuario theUser, String verificationToken) {
        casoDeUsoGuardarToken.saveUserVerificationToken(theUser, verificationToken);
    }

    @Override
    public Tokentemp buscarToken(String token) {
        return casoDeUsoBuscarToken.buscarToken(token);
    }

    @Override
    public String validateToken(String theToken) {
        return casoDeUsoValidarToken.validateToken(theToken);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return casoDeUsoBuscarPorEmail.findByEmail(email);
    }

    @Override
    public void reenviarTokenVerificacion(String email) {
        casoDeUsoReenviarVerificacion.reenviarTokenVerificacion(email);
    }

    @Override
    public void solicitarRecuperacion(String email) {
        casoDeUsoSolicitarRecuperacion.solicitarRecuperacion(email);
    }

    @Override
    public String validarTokenRecuperacion(String token) {
        return casoDeUsoRestablecerContrasenia.validarTokenRecuperacion(token);
    }

    @Override
    public void restablecerContrasenia(String token, String nuevaContrasenia) {
        casoDeUsoRestablecerContrasenia.restablecerContrasenia(token, nuevaContrasenia);
    }

}
