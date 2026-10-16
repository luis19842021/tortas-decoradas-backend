package com.tortas.decoradas.Auth.dominio.puerto.in;

import com.tortas.decoradas.Auth.dominio.modelo.Tokentemp;

public interface CasoDeUsoBuscarToken {
    Tokentemp buscarToken(String token);
}
