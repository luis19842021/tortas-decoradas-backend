package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeBizcochuelos;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarBizcochuelo;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoGuardarBizcochueloImpl implements CasoDeUsoGuardarBizcochuelo {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoGuardarBizcochueloImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public ListaDeBizcochuelos guardarBizcochuelo(ListaDeBizcochuelos bizcochuelo) {
        return puertoRepositorioProducto.guardarBizcochuelo(bizcochuelo);
    }

}
