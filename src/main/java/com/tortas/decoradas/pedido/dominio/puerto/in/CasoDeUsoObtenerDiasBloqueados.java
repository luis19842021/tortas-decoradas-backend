package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;

public interface CasoDeUsoObtenerDiasBloqueados {
    List<DiaBloqueado> obtenerDiasBloqueados();
}
