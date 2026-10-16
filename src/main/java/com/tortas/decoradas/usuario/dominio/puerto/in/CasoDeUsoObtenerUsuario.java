package com.tortas.decoradas.usuario.dominio.puerto.in;

import java.util.List;
import java.util.Optional;

import com.tortas.decoradas.usuario.dominio.modelo.PaginaUsuarios;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface CasoDeUsoObtenerUsuario {
     List<Usuario> obtenerTodosLosUsuarios();

     Optional<Usuario> buscarPorEmail(String email);

     PaginaUsuarios obtenerUsuariosPaginado(String busqueda, int pagina, int tamanio);
}
