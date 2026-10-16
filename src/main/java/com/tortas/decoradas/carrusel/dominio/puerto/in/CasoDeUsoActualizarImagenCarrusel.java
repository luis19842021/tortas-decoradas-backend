package com.tortas.decoradas.carrusel.dominio.puerto.in;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;

public interface CasoDeUsoActualizarImagenCarrusel {
    ImagenCarrusel actualizarImagenCarrusel(Integer espacio, MultipartFile file) throws IOException;
}
