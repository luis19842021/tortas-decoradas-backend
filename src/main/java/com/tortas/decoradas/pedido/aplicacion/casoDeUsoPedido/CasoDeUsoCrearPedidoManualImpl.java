package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCrearPedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoCrearPedidoManualImpl implements CasoDeUsoCrearPedidoManual {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoCrearPedidoManualImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public PedidoManual crearPedidoManual(LocalDate fecha, int cantidad, String motivo) {
        if (fecha == null) {
            throw new IllegalArgumentException("Falta la fecha del pedido manual.");
        }
        if (cantidad < 1) {
            throw new IllegalArgumentException("La cantidad tiene que ser al menos 1.");
        }
        String motivoFinal = (motivo == null || motivo.isBlank()) ? null : motivo.trim();
        return puertoRepositorioPedido.CrearPedidoManual(fecha, cantidad, motivoFinal);
    }
}
