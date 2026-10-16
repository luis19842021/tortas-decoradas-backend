package com.tortas.decoradas.producto.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

public interface CasoDeUsoObtenerProductosLimitSegunPisoSubcategoria {

    List<Producto> ObtenerProductoSegunPisoSubcategoria(Long piso,Long num,String subcategoria);

}
