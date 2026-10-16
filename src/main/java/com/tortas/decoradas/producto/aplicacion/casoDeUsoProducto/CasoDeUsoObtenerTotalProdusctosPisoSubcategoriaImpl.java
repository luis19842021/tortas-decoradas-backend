package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerTotalProdusctosPisoSubcategoria;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerTotalProdusctosPisoSubcategoriaImpl implements CasoDeUsoObtenerTotalProdusctosPisoSubcategoria  {
  private final PuertoRepositorioProducto puertoRepositorioProducto ;

    public CasoDeUsoObtenerTotalProdusctosPisoSubcategoriaImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Long totalProductosPisoSubcategoria(Long piso, String subcategoria) {
        return puertoRepositorioProducto.totalProductosPisoSubcategoria(piso,subcategoria);
    }

}
