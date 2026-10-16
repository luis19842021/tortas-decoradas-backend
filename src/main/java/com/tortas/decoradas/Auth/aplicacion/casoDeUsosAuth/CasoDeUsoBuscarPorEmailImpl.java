package com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth;

import java.util.Optional;

import com.tortas.decoradas.Auth.dominio.puerto.in.CasoDeUsoBuscarPorEmail;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public class CasoDeUsoBuscarPorEmailImpl implements CasoDeUsoBuscarPorEmail {

    private final PuertoRepositorioAuth puertoRepositorioAuth;

    public CasoDeUsoBuscarPorEmailImpl(PuertoRepositorioAuth puertoRepositorioAuth) {
        this.puertoRepositorioAuth = puertoRepositorioAuth;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return puertoRepositorioAuth.findByEmail(email);
    }

}
