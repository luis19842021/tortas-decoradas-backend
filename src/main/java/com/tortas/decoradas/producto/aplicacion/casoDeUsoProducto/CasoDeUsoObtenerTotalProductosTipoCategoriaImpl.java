package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerTotalProductosTipoCategoria;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerTotalProductosTipoCategoriaImpl implements CasoDeUsoObtenerTotalProductosTipoCategoria {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerTotalProductosTipoCategoriaImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public Long obtenerProductoTipoCategoria(String tipo, String categoria) {
       return puertoRepositorioProducto.obtenerProductoTipoCategoria(tipo, categoria);
    }

}
