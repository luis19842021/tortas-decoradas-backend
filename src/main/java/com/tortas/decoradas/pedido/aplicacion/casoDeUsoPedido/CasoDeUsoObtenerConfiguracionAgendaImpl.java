package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoObtenerConfiguracionAgendaImpl implements CasoDeUsoObtenerConfiguracionAgenda {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoObtenerConfiguracionAgendaImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public ConfiguracionAgenda obtenerConfiguracionAgenda() {
        return puertoRepositorioPedido.ObtenerConfiguracionAgenda();
    }
}
