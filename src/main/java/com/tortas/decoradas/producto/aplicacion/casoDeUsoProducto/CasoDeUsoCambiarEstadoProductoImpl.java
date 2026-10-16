package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.NoSuchElementException;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCambiarEstadoProducto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoCambiarEstadoProductoImpl implements CasoDeUsoCambiarEstadoProducto {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoCambiarEstadoProductoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Producto cambiarEstado(Long idProducto, boolean suspendido) {
        Producto producto = puertoRepositorioProducto.obtenerProductoPorId(idProducto)
                .orElseThrow(() -> new NoSuchElementException("No existe un producto con id " + idProducto));

        if (producto.isSuspendido() == suspendido) {
            return producto;
        }

        producto.setSuspendido(suspendido);
        return puertoRepositorioProducto.guardar(producto);
    }

}
