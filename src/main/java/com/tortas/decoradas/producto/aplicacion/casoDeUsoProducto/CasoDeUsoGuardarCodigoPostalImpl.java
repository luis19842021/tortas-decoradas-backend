package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarCodigoPostal;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoGuardarCodigoPostalImpl implements CasoDeUsoGuardarCodigoPostal {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoGuardarCodigoPostalImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public ListaDeCodigosPostales guardarCodigoPostal(ListaDeCodigosPostales codigoPostal) {
        return puertoRepositorioProducto.guardarCodigoPostal(codigoPostal);
    }

}
