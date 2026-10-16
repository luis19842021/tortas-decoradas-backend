package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerListaDeBizcochuelosImpl implements CasoDeUsoObtenerListaDeBizcochuelos {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerListaDeBizcochuelosImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<ListaDeBizcochuelos> obtenerListaDeBizcochuelos() {
        return puertoRepositorioProducto.obtenerListaDeBizcochuelos();
    }

}
