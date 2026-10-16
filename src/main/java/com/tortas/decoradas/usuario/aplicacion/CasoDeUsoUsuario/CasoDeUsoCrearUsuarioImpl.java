package com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCrearUsuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoCrearUsuarioImpl implements CasoDeUsoCrearUsuario {

    private final PuertoRepositorioUsuario puertoRepositorioUsuario;

    public CasoDeUsoCrearUsuarioImpl(PuertoRepositorioUsuario puertoRepositorioUsuario) {
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
    }

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return puertoRepositorioUsuario.guardar(usuario);
	}

}
