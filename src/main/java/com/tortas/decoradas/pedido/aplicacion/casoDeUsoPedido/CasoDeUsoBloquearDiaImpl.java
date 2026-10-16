package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoBloquearDia;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

import java.time.LocalDate;

public class CasoDeUsoBloquearDiaImpl implements CasoDeUsoBloquearDia {

    private static final String MOTIVO_POR_DEFECTO = "Bloqueado por el administrador";

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoBloquearDiaImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public DiaBloqueado bloquearDia(LocalDate fecha, String motivo) {
        if (fecha == null) {
            throw new IllegalArgumentException("Falta la fecha a bloquear.");
        }
        if (puertoRepositorioPedido.EstaDiaBloqueado(fecha)) {
            throw new IllegalArgumentException("Ese día ya está bloqueado.");
        }

        String motivoFinal = (motivo == null || motivo.isBlank()) ? MOTIVO_POR_DEFECTO : motivo.trim();
        return puertoRepositorioPedido.BloquearDia(fecha, motivoFinal);
    }
}
