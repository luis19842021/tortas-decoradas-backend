package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosLimitSegunPisoSubcategoria;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerProductoLimitSegunPisoSubcategoriaImpl implements CasoDeUsoObtenerProductosLimitSegunPisoSubcategoria {
   private final PuertoRepositorioProducto puertoRepositorioProducto ;

    public CasoDeUsoObtenerProductoLimitSegunPisoSubcategoriaImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<Producto> ObtenerProductoSegunPisoSubcategoria(Long piso, Long num, String subcategoria) {
       return puertoRepositorioProducto.ObtenerProductoSegunPisoSubcategoria(piso, num, subcategoria);
    }

}
