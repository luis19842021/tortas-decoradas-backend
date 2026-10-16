package com.tortas.decoradas.Auth.dominio.puerto.in;

import java.util.Optional;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface CasoDeUsoBuscarPorEmail {
     Optional<Usuario> findByEmail(String email);
}
