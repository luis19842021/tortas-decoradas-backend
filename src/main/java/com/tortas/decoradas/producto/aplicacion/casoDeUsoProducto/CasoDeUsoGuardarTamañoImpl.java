package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeTamaños;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarTamaño;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoGuardarTamañoImpl implements CasoDeUsoGuardarTamaño {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoGuardarTamañoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public ListaDeTamaños guardarTamaño(ListaDeTamaños tamaño) {
        return puertoRepositorioProducto.guardarTamaño(tamaño);
    }

}
