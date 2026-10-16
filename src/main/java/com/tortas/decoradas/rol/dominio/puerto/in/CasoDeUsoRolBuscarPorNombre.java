package com.tortas.decoradas.rol.dominio.puerto.in;

import java.util.Optional;

import com.tortas.decoradas.rol.dominio.modelo.Rol;

public interface CasoDeUsoRolBuscarPorNombre {
        Optional<Rol> findByNombre(String nombre);
}
