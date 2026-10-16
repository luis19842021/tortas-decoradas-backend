package com.tortas.decoradas.usuario.dominio.puerto.in;

public interface CasoDeUsoCambiarContrasenia {

    void cambiarContrasenia(String email, String contraseniaActual, String contraseniaNueva);

}
