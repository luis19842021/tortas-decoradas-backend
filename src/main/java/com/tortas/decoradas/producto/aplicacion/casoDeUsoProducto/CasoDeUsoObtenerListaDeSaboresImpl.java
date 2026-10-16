package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeSabores;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerListaDeSaboresImpl implements CasoDeUsoObtenerListaDeSabores {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerListaDeSaboresImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<ListaDeSabores> obtenerListaDeSabores() {
       return puertoRepositorioProducto.obtenerListaDeSabores();
    }

}
