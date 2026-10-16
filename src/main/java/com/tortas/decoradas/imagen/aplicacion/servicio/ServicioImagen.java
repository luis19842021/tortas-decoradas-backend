package com.tortas.decoradas.imagen.aplicacion.servicio;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.imagen.dominio.puerto.in.CasoDeUsoGuardarImagen;

@Service
public class ServicioImagen implements CasoDeUsoGuardarImagen {

    private final CasoDeUsoGuardarImagen casoDeUsoGuardarImagen;

    public ServicioImagen(@Lazy CasoDeUsoGuardarImagen casoDeUsoGuardarImagen) {
        this.casoDeUsoGuardarImagen = casoDeUsoGuardarImagen;
    }

    @Override
    public String guardarImagen(MultipartFile file) throws IOException {
        return casoDeUsoGuardarImagen.guardarImagen(file) ;
    }

}
