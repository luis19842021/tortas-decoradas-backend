package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;

public interface CasoDeUsoBloquearDia {
    DiaBloqueado bloquearDia(LocalDate fecha, String motivo);
}
