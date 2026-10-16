package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.Optional;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoBuscarPorNombreProducto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoBuscarPorNombreProductoImpl implements CasoDeUsoBuscarPorNombreProducto{
    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoBuscarPorNombreProductoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
     return   puertoRepositorioProducto.buscarPorNombre(nombre);
    }

}
