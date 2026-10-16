package com.tortas.decoradas.usuario.dominio.puerto.out;

import java.util.List;
import java.util.Optional;

import com.tortas.decoradas.usuario.dominio.modelo.PaginaUsuarios;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface PuertoRepositorioUsuario {
      Usuario guardar(Usuario usuario);

      List<Usuario> BuscarTtodosLosUsuarios();
      Optional <Usuario> buscarPorEmail(String email);
      Optional <Usuario> buscarPorId(Long id);

      List<Usuario> buscarAdmins();

      PaginaUsuarios buscarPaginado(String busqueda, int pagina, int tamanio);

}
