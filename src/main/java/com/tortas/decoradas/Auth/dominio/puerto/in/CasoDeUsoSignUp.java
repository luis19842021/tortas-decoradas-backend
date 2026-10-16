package com.tortas.decoradas.Auth.dominio.puerto.in;

public interface CasoDeUsoSignUp {
    String signUp(String nombre, String apellido, String contraseña, String email, String telefono);
}
