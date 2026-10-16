package com.tortas.decoradas.producto.dominio.puerto.in;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

public interface CasoDeUsoActualizarProducto {

    Producto actualizarProducto(Long id, Producto cambios);

}
