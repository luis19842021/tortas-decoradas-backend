package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoBuscarProductosPorNombre;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoBuscarProductosPorNombreImpl implements CasoDeUsoBuscarProductosPorNombre {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoBuscarProductosPorNombreImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return puertoRepositorioProducto.buscarProductosPorNombre(nombre);
    }

}
