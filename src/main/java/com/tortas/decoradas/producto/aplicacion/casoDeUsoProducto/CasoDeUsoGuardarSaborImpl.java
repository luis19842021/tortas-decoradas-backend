package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.modelo.ListaDeSabores;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoGuardarSabor;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoGuardarSaborImpl implements CasoDeUsoGuardarSabor {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoGuardarSaborImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public ListaDeSabores guardarSabor(ListaDeSabores sabor) {
        return puertoRepositorioProducto.guardarSabor(sabor);
    }

}
