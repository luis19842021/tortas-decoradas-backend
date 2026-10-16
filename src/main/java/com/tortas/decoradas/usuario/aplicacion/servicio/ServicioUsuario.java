package com.tortas.decoradas.usuario.aplicacion.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tortas.decoradas.usuario.dominio.modelo.ActualizarPerfilRequestDto;
import com.tortas.decoradas.usuario.dominio.modelo.PaginaUsuarios;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoActualizarPerfil;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCambiarContrasenia;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCambiarEstadoUsuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCambiarRolUsuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCrearUsuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoObtenerUsuario;

@Service
public class ServicioUsuario implements CasoDeUsoCrearUsuario, CasoDeUsoObtenerUsuario, CasoDeUsoActualizarPerfil,
        CasoDeUsoCambiarContrasenia, CasoDeUsoCambiarRolUsuario, CasoDeUsoCambiarEstadoUsuario {

    private final CasoDeUsoCrearUsuario casoDeUsoCrearUsuario;
    private final CasoDeUsoObtenerUsuario casoDeUsoObtenerUsuario;
    private final CasoDeUsoActualizarPerfil casoDeUsoActualizarPerfil;
    private final CasoDeUsoCambiarContrasenia casoDeUsoCambiarContrasenia;
    private final CasoDeUsoCambiarRolUsuario casoDeUsoCambiarRolUsuario;
    private final CasoDeUsoCambiarEstadoUsuario casoDeUsoCambiarEstadoUsuario;

    public ServicioUsuario(CasoDeUsoCrearUsuario casoDeUsoCrearUsuario, CasoDeUsoObtenerUsuario casoDeUsoObtenerUsuario,
            CasoDeUsoActualizarPerfil casoDeUsoActualizarPerfil,
            CasoDeUsoCambiarContrasenia casoDeUsoCambiarContrasenia,
            CasoDeUsoCambiarRolUsuario casoDeUsoCambiarRolUsuario,
            CasoDeUsoCambiarEstadoUsuario casoDeUsoCambiarEstadoUsuario) {
        this.casoDeUsoCrearUsuario = casoDeUsoCrearUsuario;
        this.casoDeUsoObtenerUsuario=casoDeUsoObtenerUsuario;
        this.casoDeUsoActualizarPerfil = casoDeUsoActualizarPerfil;
        this.casoDeUsoCambiarContrasenia = casoDeUsoCambiarContrasenia;
        this.casoDeUsoCambiarRolUsuario = casoDeUsoCambiarRolUsuario;
        this.casoDeUsoCambiarEstadoUsuario = casoDeUsoCambiarEstadoUsuario;
    }

	@Override
	public Usuario crearUsuario(Usuario usuario) {
      return casoDeUsoCrearUsuario.crearUsuario(usuario);
	}

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
     return casoDeUsoObtenerUsuario.obtenerTodosLosUsuarios();
    }

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
	 return	casoDeUsoObtenerUsuario.buscarPorEmail(email);
	}

	@Override
	public PaginaUsuarios obtenerUsuariosPaginado(String busqueda, int pagina, int tamanio) {
		return casoDeUsoObtenerUsuario.obtenerUsuariosPaginado(busqueda, pagina, tamanio);
	}

    @Override
    public Usuario actualizarPerfil(String email, ActualizarPerfilRequestDto datos) {
        return casoDeUsoActualizarPerfil.actualizarPerfil(email, datos);
    }

    @Override
    public void cambiarContrasenia(String email, String contraseniaActual, String contraseniaNueva) {
        casoDeUsoCambiarContrasenia.cambiarContrasenia(email, contraseniaActual, contraseniaNueva);
    }

    @Override
    public Usuario cambiarRol(Long idUsuario, String nombreRolNuevo, String emailAdminQueOpera) {
        return casoDeUsoCambiarRolUsuario.cambiarRol(idUsuario, nombreRolNuevo, emailAdminQueOpera);
    }

    @Override
    public Usuario cambiarEstado(Long idUsuario, boolean suspendida, String emailAdminQueOpera) {
        return casoDeUsoCambiarEstadoUsuario.cambiarEstado(idUsuario, suspendida, emailAdminQueOpera);
    }

}
