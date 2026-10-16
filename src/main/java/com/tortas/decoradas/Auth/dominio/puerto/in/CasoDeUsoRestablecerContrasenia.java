package com.tortas.decoradas.Auth.dominio.puerto.in;

public interface CasoDeUsoRestablecerContrasenia {
    String validarTokenRecuperacion(String token);

    void restablecerContrasenia(String token, String nuevaContrasenia);
}
