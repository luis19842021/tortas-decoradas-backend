package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeTamaños;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerListaDeTamañosImpl implements CasoDeUsoObtenerListaDeTamaños {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerListaDeTamañosImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<ListaDeTamaños> obtenerListaDeTamaños() {
        return puertoRepositorioProducto.obtenerListaDeTamaños();
    }

}
