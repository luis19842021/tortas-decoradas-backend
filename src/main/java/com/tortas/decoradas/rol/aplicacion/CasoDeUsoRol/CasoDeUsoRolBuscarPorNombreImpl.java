package com.tortas.decoradas.rol.aplicacion.CasoDeUsoRol;

import java.util.Optional;

import com.tortas.decoradas.rol.dominio.modelo.Rol;
import com.tortas.decoradas.rol.dominio.puerto.in.CasoDeUsoRolBuscarPorNombre;
import com.tortas.decoradas.rol.dominio.puerto.out.PuertoRepositorioRol;

public class CasoDeUsoRolBuscarPorNombreImpl implements CasoDeUsoRolBuscarPorNombre {
    private final PuertoRepositorioRol puertoRepositorioRol;

    public CasoDeUsoRolBuscarPorNombreImpl(PuertoRepositorioRol puertoRepositorioRol) {
        this.puertoRepositorioRol = puertoRepositorioRol;
    }

    @Override
    public Optional<Rol> findByNombre(String nombre) {
        return puertoRepositorioRol.findByNombre(nombre);
    }

}
