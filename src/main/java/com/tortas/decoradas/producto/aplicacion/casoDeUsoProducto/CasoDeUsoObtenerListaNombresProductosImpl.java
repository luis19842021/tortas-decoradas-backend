package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaNombreProductos;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerListaNombresProductosImpl implements CasoDeUsoObtenerListaNombreProductos {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerListaNombresProductosImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<String> listadeNombresPoroductos() {
       return  puertoRepositorioProducto.listadeNombresPoroductos();
    }

}
