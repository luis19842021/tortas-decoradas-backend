package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeCategoriasSabor;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerListaDeCategoriasSaborImpl implements CasoDeUsoObtenerListaDeCategoriasSabor {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerListaDeCategoriasSaborImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<CategoriaSabor> obtenerListaDeCategoriasSabor() {
        return puertoRepositorioProducto.obtenerListaDeCategoriasSabor();
    }

}
