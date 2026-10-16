package com.tortas.decoradas.carrusel.dominio.puerto.in;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;

public interface CasoDeUsoActualizarEnlaceCarrusel {
    ImagenCarrusel actualizarEnlaceCarrusel(Integer espacio, String enlace);
}
