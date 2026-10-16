package com.tortas.decoradas.usuario.dominio.puerto.in;

import com.tortas.decoradas.usuario.dominio.modelo.ActualizarPerfilRequestDto;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;

public interface CasoDeUsoActualizarPerfil {

    Usuario actualizarPerfil(String email, ActualizarPerfilRequestDto datos);

}
