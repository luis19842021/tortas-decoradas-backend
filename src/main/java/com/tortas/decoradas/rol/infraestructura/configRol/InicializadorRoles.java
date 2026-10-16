package com.tortas.decoradas.rol.infraestructura.configRol;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.rol.infraestructura.entiRol.EntidadRol;
import com.tortas.decoradas.rol.infraestructura.repoRol.JpaRepositorioRol;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InicializadorRoles implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(InicializadorRoles.class);

    private static final List<String> ROLES = List.of("USER", "ADMIN");

    private final JpaRepositorioRol jpaRepositorioRol;

    public InicializadorRoles(JpaRepositorioRol jpaRepositorioRol) {
        this.jpaRepositorioRol = jpaRepositorioRol;
    }

    @Override
    public void run(ApplicationArguments args) {
        for (String nombre : ROLES) {
            if (jpaRepositorioRol.findByNombre(nombre).isEmpty()) {
                jpaRepositorioRol.save(new EntidadRol(null, nombre));
                log.info("Rol {} creado.", nombre);
            }
        }
    }
}
