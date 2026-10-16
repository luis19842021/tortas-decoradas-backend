package com.tortas.decoradas.producto.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

public interface CasoDeUsoObtenerProductoLimitSegunCatSub {
    List<Producto> obtenerLimitSegunCatSub(String categoria , String subcategoria, Long offset);
}
