package com.tortas.decoradas.producto.dominio.puerto.in;

public interface CasoDeUsoObtenerTotalProductosPorTipo {
    Long totalProductosPoTipo(String tipo, String categoria);
}
