package com.tortas.decoradas.rol.infraestructura.repoRol;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.rol.infraestructura.entiRol.EntidadRol;

public interface JpaRepositorioRol extends JpaRepository<EntidadRol, Long> {

    Optional<EntidadRol> findByNombre(String name);
}
