package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.modelo.CategoriaSabor;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarCategoriaSabor;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoGuardarCategoriaSaborImpl implements CasoDeUsoGuardarCategoriaSabor {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoGuardarCategoriaSaborImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public CategoriaSabor guardarCategoriaSabor(CategoriaSabor categoriaSabor) {
        return puertoRepositorioProducto.guardarCategoriaSabor(categoriaSabor);
    }

}
