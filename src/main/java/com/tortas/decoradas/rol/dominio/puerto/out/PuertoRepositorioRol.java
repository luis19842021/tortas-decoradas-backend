package com.tortas.decoradas.rol.dominio.puerto.out;

import java.util.Optional;

import com.tortas.decoradas.rol.dominio.modelo.Rol;

public interface PuertoRepositorioRol {
    Optional<Rol> findByNombre(String nombre);
}
