package com.tortas.decoradas.imagen.dominio.puerto.out;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PuertoImagen {
    String guardarImagen(MultipartFile file) throws IOException;

}
