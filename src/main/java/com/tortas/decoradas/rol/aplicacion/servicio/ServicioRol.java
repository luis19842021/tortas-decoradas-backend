package com.tortas.decoradas.rol.aplicacion.servicio;

import java.util.Optional;

import com.tortas.decoradas.rol.dominio.modelo.Rol;
import com.tortas.decoradas.rol.dominio.puerto.in.CasoDeUsoRolBuscarPorNombre;

public class ServicioRol implements CasoDeUsoRolBuscarPorNombre {
    private final CasoDeUsoRolBuscarPorNombre casoDeUsoRolBuscarPorNombre;

    public ServicioRol(CasoDeUsoRolBuscarPorNombre casoDeUsoRolBuscarPorNombre) {
        this.casoDeUsoRolBuscarPorNombre = casoDeUsoRolBuscarPorNombre;
    }

    @Override
    public Optional<Rol> findByNombre(String nombre) {
        return casoDeUsoRolBuscarPorNombre.findByNombre(nombre);
    }

}
