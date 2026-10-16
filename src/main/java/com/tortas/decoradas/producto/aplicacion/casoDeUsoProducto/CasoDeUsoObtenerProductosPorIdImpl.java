package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.Optional;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProductoPorId;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerProductosPorIdImpl implements CasoDeUsoObtenerProductoPorId {

 private final PuertoRepositorioProducto puertoRepositorioProducto ;

    public CasoDeUsoObtenerProductosPorIdImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
       return puertoRepositorioProducto.obtenerProductoPorId(id);
    }

}
