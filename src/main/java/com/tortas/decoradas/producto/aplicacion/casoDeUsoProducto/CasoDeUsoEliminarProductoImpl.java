package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.NoSuchElementException;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoEliminarProducto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoEliminarProductoImpl implements CasoDeUsoEliminarProducto {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoEliminarProductoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public void eliminarProducto(Long id) {
        if (puertoRepositorioProducto.obtenerProductoPorId(id).isEmpty()) {
            throw new NoSuchElementException("No existe un producto con id " + id);
        }
        puertoRepositorioProducto.eliminarProducto(id);
    }

}
