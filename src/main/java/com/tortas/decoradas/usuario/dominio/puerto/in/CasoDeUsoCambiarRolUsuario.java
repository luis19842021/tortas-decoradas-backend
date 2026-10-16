package com.tortas.decoradas.usuario.dominio.puerto.in;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface CasoDeUsoCambiarRolUsuario {

    Usuario cambiarRol(Long idUsuario, String nombreRolNuevo, String emailAdminQueOpera);

}
