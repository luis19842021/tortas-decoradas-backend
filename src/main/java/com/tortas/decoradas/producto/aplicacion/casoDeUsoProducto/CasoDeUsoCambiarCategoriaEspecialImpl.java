package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCambiarCategoriaEspecial;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoCambiarCategoriaEspecialImpl implements CasoDeUsoCambiarCategoriaEspecial {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoCambiarCategoriaEspecialImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public void cambiarCategoriaEspecial(String tipo,Long id) {
        puertoRepositorioProducto.cambiarCategoriaEspecial(tipo,id);
    }

}
