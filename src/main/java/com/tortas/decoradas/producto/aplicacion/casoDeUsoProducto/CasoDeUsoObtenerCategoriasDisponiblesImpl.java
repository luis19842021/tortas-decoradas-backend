package com.tortas.decoradas.producto.aplicacion.casoDeUsoProducto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tortas.decoradas.producto.dominio.modelo.Categoria;
import com.tortas.decoradas.producto.dominio.modelo.CategoriaDisponible;
import com.tortas.decoradas.producto.dominio.puerto.in.CasoDeUsoObtenerCategoriasDisponibles;

public class CasoDeUsoObtenerCategoriasDisponiblesImpl implements CasoDeUsoObtenerCategoriasDisponibles {

    @Override
    public List<CategoriaDisponible> obtenerCategoriasDisponibles() {
        return Arrays.stream(Categoria.values())
                .filter(categoria -> categoria != Categoria.NINGUNO)
                .map(categoria -> new CategoriaDisponible(categoria, categoria.getTipoAsociado()))
                .collect(Collectors.toList());
    }

}
