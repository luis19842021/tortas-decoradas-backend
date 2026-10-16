package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoCambiarDosCatEspProducto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoCambiarDosCatEspProductoImpl implements CasoDeUsoCambiarDosCatEspProducto {

    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoCambiarDosCatEspProductoImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public void cambiarDosCatEspecial(String tipoV, Long idV, String tipoN, Long idN) {
        puertoRepositorioProducto.cambiarDosCatEspecial(tipoV,idV,tipoN, idN);
    }

}
