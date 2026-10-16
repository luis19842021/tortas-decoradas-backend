package com.tortas.decoradas.pedido.dominio.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class ReglasDeAgenda {

    public static final int DIAS_MINIMOS_DE_ANTICIPACION = 2;

    private ReglasDeAgenda() {
    }

    public static boolean respetaAnticipacionMinima(LocalDate fecha, LocalDate hoy) {
        if (fecha == null || hoy == null) {
            return false;
        }
        long diffDias = ChronoUnit.DAYS.between(hoy, fecha);
        return diffDias >= DIAS_MINIMOS_DE_ANTICIPACION;
    }
}
