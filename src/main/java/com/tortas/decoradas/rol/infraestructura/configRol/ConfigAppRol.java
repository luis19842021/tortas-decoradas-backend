package com.tortas.decoradas.rol.infraestructura.configRol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tortas.decoradas.rol.aplicacion.CasoDeUsoRol.CasoDeUsoRolBuscarPorNombreImpl;
import com.tortas.decoradas.rol.aplicacion.servicio.ServicioRol;
import com.tortas.decoradas.rol.dominio.puerto.out.PuertoRepositorioRol;

@Configuration
public class ConfigAppRol {

    @Bean
    public ServicioRol servicioRol(PuertoRepositorioRol puertoRepositorioRol) {
        return new ServicioRol(
                new CasoDeUsoRolBuscarPorNombreImpl(puertoRepositorioRol)
        );
    }
}
