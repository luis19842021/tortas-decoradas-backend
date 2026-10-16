package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoProductosTotalSubcategoria;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoProductosTotalSubcategoriaImpl implements CasoDeUsoProductosTotalSubcategoria {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoProductosTotalSubcategoriaImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Long totalProductosSubcategoria(String categoria, String subcategoria) {
       return puertoRepositorioProducto.totalProductosSubcategoria(categoria, subcategoria);
    }

}
