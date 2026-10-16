package com.tortas.decoradas.Auth.dominio.modelo;

public record AuthRequestDto(
        String nombre,
        String apellido,
        String contraseña,
        String email,
        String telefono
) {

}
