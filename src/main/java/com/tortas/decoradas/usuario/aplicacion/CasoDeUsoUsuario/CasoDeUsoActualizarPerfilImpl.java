package com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario;

import com.tortas.decoradas.usuario.dominio.modelo.ActualizarPerfilRequestDto;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoActualizarPerfil;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoActualizarPerfilImpl implements CasoDeUsoActualizarPerfil {

    private final PuertoRepositorioUsuario puertoRepositorioUsuario;

    public CasoDeUsoActualizarPerfilImpl(PuertoRepositorioUsuario puertoRepositorioUsuario) {
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
    }

    @Override
    public Usuario actualizarPerfil(String email, ActualizarPerfilRequestDto datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new IllegalArgumentException("Falta el nombre.");
        }
        if (datos.getApellido() == null || datos.getApellido().isBlank()) {
            throw new IllegalArgumentException("Falta el apellido.");
        }

        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setTelefono(datos.getTelefono());

        return puertoRepositorioUsuario.guardar(usuario);
    }
}
