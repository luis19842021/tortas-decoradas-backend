package com.tortas.decoradas.producto.dominio.puerto.in;

import java.util.Optional;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

public interface CasoDeUsoObtenerProductoPorId {
    Optional<Producto> obtenerPorId(Long id);
}
