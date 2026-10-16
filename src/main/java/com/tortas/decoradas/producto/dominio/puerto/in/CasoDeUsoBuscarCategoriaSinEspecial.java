package com.tortas.decoradas.producto.dominio.puerto.in;

import java.util.List;

import com.tortas.decoradas.producto.dominio.modelo.Producto;

public interface CasoDeUsoBuscarCategoriaSinEspecial {

    List<Producto> buscarCategoriaSinEspecial(String tipo, String nombre);

}
