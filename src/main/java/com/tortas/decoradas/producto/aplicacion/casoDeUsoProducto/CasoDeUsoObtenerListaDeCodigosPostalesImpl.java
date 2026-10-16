package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerListaDeCodigosPostalesImpl implements CasoDeUsoObtenerListaDeCodigosPostales {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerListaDeCodigosPostalesImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<ListaDeCodigosPostales> obtenerListaDeCodigosPostales() {
        return puertoRepositorioProducto.obtenerListaDeCodigosPostales();
    }

}
