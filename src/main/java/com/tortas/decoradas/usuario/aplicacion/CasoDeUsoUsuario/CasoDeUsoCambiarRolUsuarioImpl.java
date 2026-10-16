package com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tortas.decoradas.rol.dominio.modelo.Rol;
import com.tortas.decoradas.rol.dominio.puerto.out.PuertoRepositorioRol;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCambiarRolUsuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoCambiarRolUsuarioImpl implements CasoDeUsoCambiarRolUsuario {

    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoRepositorioRol puertoRepositorioRol;

    public CasoDeUsoCambiarRolUsuarioImpl(PuertoRepositorioUsuario puertoRepositorioUsuario,
            PuertoRepositorioRol puertoRepositorioRol) {
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoRepositorioRol = puertoRepositorioRol;
    }

    @Override
    public Usuario cambiarRol(Long idUsuario, String nombreRolNuevo, String emailAdminQueOpera) {
        if (nombreRolNuevo == null
                || (!nombreRolNuevo.equalsIgnoreCase("ADMIN") && !nombreRolNuevo.equalsIgnoreCase("USER"))) {
            throw new IllegalArgumentException("El rol tiene que ser ADMIN o USER.");
        }

        Usuario usuario = puertoRepositorioUsuario.buscarPorId(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        boolean esActualmenteAdmin = usuario.getRoles().stream()
                .anyMatch(rol -> "ADMIN".equalsIgnoreCase(rol.getNombre()));
        boolean quiereSerAdmin = nombreRolNuevo.equalsIgnoreCase("ADMIN");

        if (quiereSerAdmin == esActualmenteAdmin) {
            return usuario;
        }

        if (!quiereSerAdmin) {
            if (usuario.getEmail() != null && usuario.getEmail().equalsIgnoreCase(emailAdminQueOpera)) {
                throw new IllegalStateException("No podés quitarte el rol de administrador a vos mismo.");
            }
            if (puertoRepositorioUsuario.buscarAdmins().size() <= 1) {
                throw new IllegalStateException("No se puede quitar el último administrador del sistema.");
            }

            List<Rol> rolesSinAdmin = usuario.getRoles().stream()
                    .filter(rol -> !"ADMIN".equalsIgnoreCase(rol.getNombre()))
                    .collect(Collectors.toList());
            usuario.setRoles(rolesSinAdmin);
        } else {
            Rol rolAdmin = puertoRepositorioRol.findByNombre("ADMIN")
                    .orElseThrow(() -> new IllegalStateException("El rol ADMIN no existe en la base."));

            List<Rol> rolesConAdmin = new ArrayList<>(usuario.getRoles());
            rolesConAdmin.add(rolAdmin);
            usuario.setRoles(rolesConAdmin);
        }

        return puertoRepositorioUsuario.guardar(usuario);
    }

}
