package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.time.LocalDate;

public interface CasoDeUsoDesbloquearDia {
    void desbloquearDia(LocalDate fecha);
}
