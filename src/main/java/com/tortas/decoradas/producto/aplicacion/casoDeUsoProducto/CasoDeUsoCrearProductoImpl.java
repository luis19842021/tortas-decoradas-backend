package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCrearProducto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoCrearProductoImpl implements CasoDeUsoCrearProducto  {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoCrearProductoImpl(PuertoRepositorioProducto puertoRepositorioProducto){
        this.puertoRepositorioProducto=puertoRepositorioProducto;
    }

    @Override
    public Producto creaProducto(Producto producto) {
        if (puertoRepositorioProducto.buscarPorNombre(producto.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con ese nombre.");
        }
        return puertoRepositorioProducto.guardar(producto);
    }

}
