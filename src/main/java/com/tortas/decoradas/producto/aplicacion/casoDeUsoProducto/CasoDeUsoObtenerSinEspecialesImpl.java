package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;
import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerSinEspeciales;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerSinEspecialesImpl implements CasoDeUsoObtenerSinEspeciales {

      private final PuertoRepositorioProducto puertoRepositorioProducto ;

    public CasoDeUsoObtenerSinEspecialesImpl(PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<Producto> obtenerSinEspeciales(String tipo) {
        return puertoRepositorioProducto.obtenerCategoriaSinEspecial(tipo);
    }

}
