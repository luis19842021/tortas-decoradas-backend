package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerTotalProductosPorTipo;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerTotalProductosPorTipoImpl implements CasoDeUsoObtenerTotalProductosPorTipo {
    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerTotalProductosPorTipoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Long totalProductosPoTipo(String tipo, String categoria) {
        return puertoRepositorioProducto.totalProductosPoTipo(tipo, categoria);
    }

}
