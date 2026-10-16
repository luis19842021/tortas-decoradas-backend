package com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCambiarEstadoUsuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoCambiarEstadoUsuarioImpl implements CasoDeUsoCambiarEstadoUsuario {

    private final PuertoRepositorioUsuario puertoRepositorioUsuario;

    public CasoDeUsoCambiarEstadoUsuarioImpl(PuertoRepositorioUsuario puertoRepositorioUsuario) {
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
    }

    @Override
    public Usuario cambiarEstado(Long idUsuario, boolean suspendida, String emailAdminQueOpera) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorId(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        if (usuario.isSuspendida() == suspendida) {
            return usuario;
        }

        if (suspendida) {
            if (usuario.getEmail() != null && usuario.getEmail().equalsIgnoreCase(emailAdminQueOpera)) {
                throw new IllegalStateException("No podés suspender tu propia cuenta.");
            }

            boolean esAdmin = usuario.getRoles().stream()
                    .anyMatch(rol -> "ADMIN".equalsIgnoreCase(rol.getNombre()));
            if (esAdmin && puertoRepositorioUsuario.buscarAdmins().size() <= 1) {
                throw new IllegalStateException("No se puede suspender al último administrador del sistema.");
            }
        }

        usuario.setSuspendida(suspendida);
        return puertoRepositorioUsuario.guardar(usuario);
    }

}
