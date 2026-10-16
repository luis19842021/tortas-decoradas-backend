package com.tortas.decoradas.imagen.aplicacion.casoDeUsosDeImagen;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.imagen.dominio.puerto.in.CasoDeUsoGuardarImagen;
import com.tortas.decoradas.imagen.dominio.puerto.out.PuertoImagen;

public class CasoDeUsoGuardarImagenImpl implements CasoDeUsoGuardarImagen {

    private final PuertoImagen puertoImagen;

    public CasoDeUsoGuardarImagenImpl(PuertoImagen puertoImagen) {
        this.puertoImagen = puertoImagen;
    }
    @Override
    public String guardarImagen(MultipartFile file) throws IOException {
        return puertoImagen.guardarImagen(file);
    }

}
