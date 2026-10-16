package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoDesbloquearDia;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoDesbloquearDiaImpl implements CasoDeUsoDesbloquearDia {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoDesbloquearDiaImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public void desbloquearDia(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("Falta la fecha a desbloquear.");
        }
        if (!puertoRepositorioPedido.EstaDiaBloqueado(fecha)) {
            throw new IllegalArgumentException("Ese día no está bloqueado.");
        }
        puertoRepositorioPedido.DesbloquearDia(fecha);
    }
}
