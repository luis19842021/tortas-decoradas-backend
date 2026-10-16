package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoBuscarCategoriaSinEspecial;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoBuscarCategoriaSinEspecialImpl implements CasoDeUsoBuscarCategoriaSinEspecial {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoBuscarCategoriaSinEspecialImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<Producto> buscarCategoriaSinEspecial(String tipo, String nombre) {
        return puertoRepositorioProducto.buscarCategoriaSinEspecial(tipo, nombre);
    }

}
