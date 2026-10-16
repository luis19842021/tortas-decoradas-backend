package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductosLimitSegunTipo;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerProductosLimitSegunTipoImpl implements CasoDeUsoObtenerProductosLimitSegunTipo {

      private final PuertoRepositorioProducto puertoRepositorioProducto ;

    public CasoDeUsoObtenerProductosLimitSegunTipoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }
    @Override
    public List<Producto> obtenerLimitSegunTipo(String tipo, String categoria, Long offset) {
    return puertoRepositorioProducto.obtenerLimitSegunTipo(tipo,categoria, offset);
    }

}
