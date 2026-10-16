package com.tortas.decoradas.usuario.infraestructura.adaptaUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.usuario.dominio.modelo.PaginaUsuarios;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;
import com.tortas.decoradas.usuario.infraestructura.repoUser.JpaRepositorioUsuario;

@Component
public class JpaRepositorioUsuarioAdaptador implements PuertoRepositorioUsuario {

    private final JpaRepositorioUsuario jpaRepositorioUsuario;

	public JpaRepositorioUsuarioAdaptador(JpaRepositorioUsuario jpaRepositorioUsuario ) {
		this.jpaRepositorioUsuario = jpaRepositorioUsuario;

	}

	@Override
	public Usuario guardar(Usuario usuario) {
		EntidadUsuario entidadUsuario= EntidadUsuario.fromDomainModel(usuario );
       EntidadUsuario guardarEntidadUsuario =jpaRepositorioUsuario.save(entidadUsuario);
       return guardarEntidadUsuario.toDomainModel();
	}

	@Override
	public List<Usuario> BuscarTtodosLosUsuarios() {

		return jpaRepositorioUsuario.findAll().stream().map(EntidadUsuario::toDomainModel).collect(Collectors.toList());
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return jpaRepositorioUsuario.findByEmail(email).map(EntidadUsuario::toDomainModel);
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		return jpaRepositorioUsuario.findById(id).map(EntidadUsuario::toDomainModel);
	}

	@Override
	public List<Usuario> buscarAdmins() {
		return jpaRepositorioUsuario.findAll().stream()
				.filter(entidad -> entidad.getRoles().stream()
						.anyMatch(rol -> "ADMIN".equalsIgnoreCase(rol.getNombre())))
				.map(EntidadUsuario::toDomainModel)
				.collect(Collectors.toList());
	}

	@Override
	public PaginaUsuarios buscarPaginado(String busqueda, int pagina, int tamanio) {
		Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("nombre").ascending());

		Page<EntidadUsuario> resultado = (busqueda == null || busqueda.isBlank())
				? jpaRepositorioUsuario.findAll(pageable)
				: jpaRepositorioUsuario.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrEmailContainingIgnoreCase(
						busqueda, busqueda, busqueda, pageable);

		List<Usuario> contenido = resultado.getContent().stream()
				.map(EntidadUsuario::toDomainModel)
				.collect(Collectors.toList());

		return new PaginaUsuarios(contenido, resultado.getTotalElements(), resultado.getTotalPages(), pagina, tamanio);
	}

}
