package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerCategoriaEspecial;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerCategoriaEspecialImpl implements CasoDeUsoObtenerCategoriaEspecial {
    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerCategoriaEspecialImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<Producto> obtenerCategoriaEspecial(String tipo) {
        return puertoRepositorioProducto.obtenerCategoriaEspecial(tipo);
    }

}
