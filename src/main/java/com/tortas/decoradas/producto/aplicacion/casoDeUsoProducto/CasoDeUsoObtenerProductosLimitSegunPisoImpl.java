package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosLimitSegunPiso;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerProductosLimitSegunPisoImpl implements CasoDeUsoObtenerProductosLimitSegunPiso {

     private final PuertoRepositorioProducto puertoRepositorioProducto ;

    public CasoDeUsoObtenerProductosLimitSegunPisoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<Producto> obtenerLimitSegunPiso(Long piso,Long offset) {
        return puertoRepositorioProducto.obtenerLimitSegunPiso(piso,offset);
    }

}
