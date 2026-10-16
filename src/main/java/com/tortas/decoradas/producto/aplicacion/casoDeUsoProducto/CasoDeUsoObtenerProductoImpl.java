package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerProducto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;

public class CasoDeUsoObtenerProductoImpl implements CasoDeUsoObtenerProducto {
    private final PuertoRepositorioProducto puertoRepositorioProducto ;

 public CasoDeUsoObtenerProductoImpl(PuertoRepositorioProducto puertoRepositorioProducto){
    this.puertoRepositorioProducto=puertoRepositorioProducto;
 }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
      return puertoRepositorioProducto.BuscarTodosLosProductos();
    }

}
