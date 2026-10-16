package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDate;

import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoIniciarCoccionAutomatica;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoIniciarCoccionAutomaticaImpl implements CasoDeUsoIniciarCoccionAutomatica {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoIniciarCoccionAutomaticaImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public int iniciarCoccionParaPedidosProximos() {
        LocalDate limite = LocalDate.now().plusDays(2);
        return puertoRepositorioPedido.IniciarCoccionAutomatica(limite);
    }
}
