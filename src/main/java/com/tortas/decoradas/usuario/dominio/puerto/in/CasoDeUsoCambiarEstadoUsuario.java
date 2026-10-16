package com.tortas.decoradas.usuario.dominio.puerto.in;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface CasoDeUsoCambiarEstadoUsuario {

    Usuario cambiarEstado(Long idUsuario, boolean suspendida, String emailAdminQueOpera);

}
