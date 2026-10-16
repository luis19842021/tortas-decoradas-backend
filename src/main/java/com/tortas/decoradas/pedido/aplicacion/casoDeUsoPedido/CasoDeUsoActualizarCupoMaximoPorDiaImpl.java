package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoActualizarCupoMaximoPorDia;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoActualizarCupoMaximoPorDiaImpl implements CasoDeUsoActualizarCupoMaximoPorDia {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoActualizarCupoMaximoPorDiaImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public ConfiguracionAgenda actualizarCupoMaximoPorDia(int nuevoCupo) {
        if (nuevoCupo < 1) {
            throw new IllegalArgumentException("El cupo máximo por día tiene que ser como mínimo 1.");
        }
        return puertoRepositorioPedido.ActualizarCupoMaximoPorDia(nuevoCupo);
    }
}
