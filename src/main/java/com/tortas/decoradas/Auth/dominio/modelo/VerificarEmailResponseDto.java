package com.tortas.decoradas.Auth.dominio.modelo;

public record VerificarEmailResponseDto(
        AuthStatus status,
        String message,
        String email) {
}
