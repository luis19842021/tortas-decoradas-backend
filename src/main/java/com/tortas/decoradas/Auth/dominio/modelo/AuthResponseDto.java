package com.tortas.decoradas.Auth.dominio.modelo;

public record AuthResponseDto(
        String token,
        AuthStatus authStatus,
        String message) {

}
