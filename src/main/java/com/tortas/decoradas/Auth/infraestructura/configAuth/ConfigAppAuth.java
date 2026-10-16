package com.tortas.decoradas.Auth.infraestructura.configAuth;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoBuscarPorEmailImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoBuscarTokenImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoGuardarTokenImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoLoginImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoReenviarVerificacionImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoRestablecerContraseniaImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoSignUpImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoSolicitarRecuperacionImpl;
import com.tortas.decoradas.Auth.aplicacion.casoDeUsosAuth.CasoDeUsoValidarTokenImpl;
import com.tortas.decoradas.Auth.aplicacion.servicio.ServicioAuth;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;

@Configuration
public class ConfigAppAuth {
    @Bean
    public ServicioAuth servicioAuth(PuertoRepositorioAuth puertoRepositorioAuth) {
        return new ServicioAuth(
                new CasoDeUsoSignUpImpl(puertoRepositorioAuth),
                new CasoDeUsoGuardarTokenImpl(puertoRepositorioAuth),
                new CasoDeUsoBuscarTokenImpl(puertoRepositorioAuth),
                new CasoDeUsoValidarTokenImpl(puertoRepositorioAuth),
                new CasoDeUsoLoginImpl(puertoRepositorioAuth),
                new CasoDeUsoBuscarPorEmailImpl(puertoRepositorioAuth),
                new CasoDeUsoReenviarVerificacionImpl(puertoRepositorioAuth),
                new CasoDeUsoSolicitarRecuperacionImpl(puertoRepositorioAuth),
                new CasoDeUsoRestablecerContraseniaImpl(puertoRepositorioAuth)
        );
    }
}
