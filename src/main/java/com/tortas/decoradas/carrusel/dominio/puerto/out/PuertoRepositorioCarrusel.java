package com.tortas.decoradas.carrusel.dominio.puerto.out;

import java.util.List;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;

public interface PuertoRepositorioCarrusel {

    List<ImagenCarrusel> obtenerTodas();

    void guardarUrl(Integer espacio, String url);

    boolean guardarEnlace(Integer espacio, String enlace);

    void eliminarUrl(Integer espacio);
}
