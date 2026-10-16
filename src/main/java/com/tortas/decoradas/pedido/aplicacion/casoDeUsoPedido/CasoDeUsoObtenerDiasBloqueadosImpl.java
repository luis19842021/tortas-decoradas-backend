package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerDiasBloqueados;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;

public class CasoDeUsoObtenerDiasBloqueadosImpl implements CasoDeUsoObtenerDiasBloqueados {

    private final PuertoRepositorioPedido puertoRepositorioPedido;

    public CasoDeUsoObtenerDiasBloqueadosImpl(PuertoRepositorioPedido puertoRepositorioPedido) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
    }

    @Override
    public List<DiaBloqueado> obtenerDiasBloqueados() {
        return puertoRepositorioPedido.ObtenerDiasBloqueados();
    }
}
