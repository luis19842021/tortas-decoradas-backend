package com.tortas.decoradas.usuario.infraestructura.configUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.rol.infraestructura.entiRol.EntidadRol;
import com.tortas.decoradas.rol.infraestructura.repoRol.JpaRepositorioRol;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;
import com.tortas.decoradas.usuario.infraestructura.repoUser.JpaRepositorioUsuario;

@Component
public class InicializadorAdminPrincipal implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(InicializadorAdminPrincipal.class);

    private final JpaRepositorioUsuario jpaRepositorioUsuario;
    private final JpaRepositorioRol jpaRepositorioRol;
    private final String emailAdminBootstrap;

    public InicializadorAdminPrincipal(JpaRepositorioUsuario jpaRepositorioUsuario, JpaRepositorioRol jpaRepositorioRol,
            @Value("${admin.bootstrap.email:}") String emailAdminBootstrap) {
        this.jpaRepositorioUsuario = jpaRepositorioUsuario;
        this.jpaRepositorioRol = jpaRepositorioRol;
        this.emailAdminBootstrap = emailAdminBootstrap;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (emailAdminBootstrap == null || emailAdminBootstrap.isBlank()) {
            return;
        }

        boolean yaHayAdmin = jpaRepositorioUsuario.findAll().stream()
                .anyMatch(usuario -> usuario.getRoles().stream()
                        .anyMatch(rol -> "ADMIN".equalsIgnoreCase(rol.getNombre())));
        if (yaHayAdmin) {
            return;
        }

        EntidadUsuario usuario = jpaRepositorioUsuario.findByEmail(emailAdminBootstrap).orElse(null);
        if (usuario == null) {
            log.warn("ADMIN_BOOTSTRAP_EMAIL ({}) está configurado pero todavía no hay ningún usuario registrado con "
                    + "ese email; no se puede asignar el admin principal hasta que se registre.", emailAdminBootstrap);
            return;
        }

        EntidadRol rolAdmin = jpaRepositorioRol.findByNombre("ADMIN").orElse(null);
        if (rolAdmin == null) {
            log.warn("No existe el rol ADMIN en la tabla rol; no se puede asignar el admin principal.");
            return;
        }

        usuario.getRoles().removeIf(rol -> "USER".equalsIgnoreCase(rol.getNombre()));
        usuario.getRoles().add(rolAdmin);
        jpaRepositorioUsuario.save(usuario);
        log.info("Admin principal asignado automáticamente a {}", emailAdminBootstrap);
    }

}
