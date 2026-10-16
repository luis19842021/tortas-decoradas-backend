package com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario;

import java.util.List;
import java.util.Optional;

import com.tortas.decoradas.usuario.dominio.modelo.PaginaUsuarios;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoObtenerUsuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoObtenerUsuarioImpl implements CasoDeUsoObtenerUsuario {
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;

    public CasoDeUsoObtenerUsuarioImpl(PuertoRepositorioUsuario puertoRepositorioUsuario) {
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
       return puertoRepositorioUsuario.BuscarTtodosLosUsuarios();
    }

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return puertoRepositorioUsuario.buscarPorEmail(email);
	}

	@Override
	public PaginaUsuarios obtenerUsuariosPaginado(String busqueda, int pagina, int tamanio) {
		int paginaSegura = Math.max(pagina, 0);
		int tamanioSeguro = tamanio <= 0 ? 20 : Math.min(tamanio, 100);
		return puertoRepositorioUsuario.buscarPaginado(busqueda, paginaSegura, tamanioSeguro);
	}

}
