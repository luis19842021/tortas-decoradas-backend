package com.tortas.decoradas.producto.dominio.puerto.in;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

public interface CasoDeUsoCambiarEstadoProducto {

    Producto cambiarEstado(Long idProducto, boolean suspendido);

}
