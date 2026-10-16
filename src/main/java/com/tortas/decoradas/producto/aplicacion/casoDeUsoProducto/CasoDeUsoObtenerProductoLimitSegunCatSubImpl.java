package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductoLimitSegunCatSub;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerProductoLimitSegunCatSubImpl implements CasoDeUsoObtenerProductoLimitSegunCatSub{
      private final PuertoRepositorioProducto puertoRepositorioProducto ;

    public CasoDeUsoObtenerProductoLimitSegunCatSubImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

      @Override
      public List<Producto> obtenerLimitSegunCatSub(String categoria, String subcategoria, Long offset) {
        return puertoRepositorioProducto.obtenerLimitSegunCatSub(categoria, subcategoria, offset);
      }

}
