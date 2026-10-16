package com.tortas.decoradas.usuario.infraestructura.configUser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tortas.decoradas.rol.dominio.puerto.out.PuertoRepositorioRol;
import com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario.CasoDeUsoActualizarPerfilImpl;
import com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario.CasoDeUsoCambiarContraseniaImpl;
import com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario.CasoDeUsoCambiarEstadoUsuarioImpl;
import com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario.CasoDeUsoCambiarRolUsuarioImpl;
import com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario.CasoDeUsoCrearUsuarioImpl;
import com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario.CasoDeUsoObtenerUsuarioImpl;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;
import com.tortas.decoradas.usuario.aplicacion.servicio.ServicioUsuario;

@Configuration
public class ConfigAppUser {

    @Bean
    public ServicioUsuario servicioUsuario(PuertoRepositorioUsuario puertoRepositorioUsuario, PasswordEncoder passwordEncoder,
            PuertoRepositorioRol puertoRepositorioRol){
        return new ServicioUsuario(
            new CasoDeUsoCrearUsuarioImpl(puertoRepositorioUsuario),
            new CasoDeUsoObtenerUsuarioImpl(puertoRepositorioUsuario),
            new CasoDeUsoActualizarPerfilImpl(puertoRepositorioUsuario),
            new CasoDeUsoCambiarContraseniaImpl(puertoRepositorioUsuario, passwordEncoder),
            new CasoDeUsoCambiarRolUsuarioImpl(puertoRepositorioUsuario, puertoRepositorioRol),
            new CasoDeUsoCambiarEstadoUsuarioImpl(puertoRepositorioUsuario));
    }

}
