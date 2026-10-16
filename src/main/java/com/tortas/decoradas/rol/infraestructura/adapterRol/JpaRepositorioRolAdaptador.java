package com.tortas.decoradas.rol.infraestructura.adapterRol;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tortas.decoradas.rol.dominio.modelo.Rol;
import com.tortas.decoradas.rol.dominio.puerto.out.PuertoRepositorioRol;
import com.tortas.decoradas.rol.infraestructura.entiRol.EntidadRol;
import com.tortas.decoradas.rol.infraestructura.repoRol.JpaRepositorioRol;

@Component
public class JpaRepositorioRolAdaptador implements PuertoRepositorioRol {

private final JpaRepositorioRol jpaRepositorioRol;

public JpaRepositorioRolAdaptador(JpaRepositorioRol jpaRepositorioRol) {
    this.jpaRepositorioRol = jpaRepositorioRol;
}

    @Override
    public Optional<Rol> findByNombre(String nombre) {
        return jpaRepositorioRol.findByNombre(nombre).map(EntidadRol::toDomainModel);
    }

}
