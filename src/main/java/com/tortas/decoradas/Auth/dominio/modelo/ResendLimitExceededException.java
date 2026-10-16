package com.tortas.decoradas.Auth.dominio.modelo;

public class ResendLimitExceededException extends RuntimeException {

    public ResendLimitExceededException(String message) {
        super(message);
    }

}
