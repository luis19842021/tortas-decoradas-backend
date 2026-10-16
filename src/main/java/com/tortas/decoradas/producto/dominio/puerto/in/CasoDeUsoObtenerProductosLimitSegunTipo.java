package com.tortas.decoradas.producto.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

public interface CasoDeUsoObtenerProductosLimitSegunTipo {
     List<Producto> obtenerLimitSegunTipo(String tipo , String categoria, Long offset);
}
