package com.tortas.decoradas.pedido.dominio.puerto.in;

import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;

public interface CasoDeUsoActualizarCupoMaximoPorDia {
    ConfiguracionAgenda actualizarCupoMaximoPorDia(int nuevoCupo);
}
