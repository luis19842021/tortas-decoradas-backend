package com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoActualizarImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.out.PuertoRepositorioCarrusel;
import com.tortas.decoradas.imagen.dominio.puerto.out.PuertoImagen;

public class CasoDeUsoActualizarImagenCarruselImpl implements CasoDeUsoActualizarImagenCarrusel {

    private final PuertoRepositorioCarrusel puertoRepositorioCarrusel;
    private final PuertoImagen puertoImagen;

    public CasoDeUsoActualizarImagenCarruselImpl(PuertoRepositorioCarrusel puertoRepositorioCarrusel,
            PuertoImagen puertoImagen) {
        this.puertoRepositorioCarrusel = puertoRepositorioCarrusel;
        this.puertoImagen = puertoImagen;
    }

    @Override
    public ImagenCarrusel actualizarImagenCarrusel(Integer espacio, MultipartFile file) throws IOException {
        String url = puertoImagen.guardarImagen(file);
        puertoRepositorioCarrusel.guardarUrl(espacio, url);
        String enlace = puertoRepositorioCarrusel.obtenerTodas().stream()
                .filter(i -> espacio.equals(i.getEspacio())).map(ImagenCarrusel::getEnlace).findFirst().orElse(null);
        return new ImagenCarrusel(espacio, url, enlace);
    }
}
