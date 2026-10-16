package com.tortas.decoradas.imagen.dominio.puerto.in;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CasoDeUsoGuardarImagen {
    String guardarImagen(MultipartFile file)throws IOException;
}
