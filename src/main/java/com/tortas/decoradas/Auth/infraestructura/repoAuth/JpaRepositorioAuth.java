package com.tortas.decoradas.Auth.infraestructura.repoAuth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

public interface JpaRepositorioAuth extends JpaRepository<EntidadUsuario, Long> {
    boolean existsByEmail(String email);

    Optional<EntidadUsuario> findByNombre(String nombre);

    Optional<EntidadUsuario> findByEmail(String email);

}
