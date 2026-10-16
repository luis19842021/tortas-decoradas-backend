package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosTotalPorPiso;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerProductosTotalPorPisoImpl implements CasoDeUsoObtenerProductosTotalPorPiso{
 private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerProductosTotalPorPisoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Long obtenerProductosTotalPorPiso(Long piso) {
       return puertoRepositorioProducto.obtenerProductosTotalPorPiso(piso);
    }

}
