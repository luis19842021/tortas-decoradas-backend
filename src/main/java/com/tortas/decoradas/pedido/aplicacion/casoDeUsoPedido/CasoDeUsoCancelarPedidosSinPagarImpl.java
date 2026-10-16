package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDateTime;

import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCancelarPedidosSinPagar;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoCancelarPedidosSinPagarImpl implements CasoDeUsoCancelarPedidosSinPagar {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoCancelarPedidosSinPagarImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public int cancelarPedidosSinPagar(int minutos) {
        if (minutos < 1) {
            throw new IllegalArgumentException("El plazo para pagar tiene que ser de al menos 1 minuto.");
        }
        return puertoRepositorioPedido.CancelarPedidosSinPagar(LocalDateTime.now().minusMinutes(minutos));
    }
}
