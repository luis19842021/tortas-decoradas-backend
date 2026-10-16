package com.tortas.decoradas.carrusel.aplicacion.servicio;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoActualizarEnlaceCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoActualizarImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoEliminarImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoObtenerCarrusel;

public class ServicioCarrusel
        implements CasoDeUsoObtenerCarrusel, CasoDeUsoActualizarImagenCarrusel, CasoDeUsoEliminarImagenCarrusel,
        CasoDeUsoActualizarEnlaceCarrusel {

    private final CasoDeUsoObtenerCarrusel casoDeUsoObtenerCarrusel;
    private final CasoDeUsoActualizarImagenCarrusel casoDeUsoActualizarImagenCarrusel;
    private final CasoDeUsoEliminarImagenCarrusel casoDeUsoEliminarImagenCarrusel;
    private final CasoDeUsoActualizarEnlaceCarrusel casoDeUsoActualizarEnlaceCarrusel;

    public ServicioCarrusel(CasoDeUsoObtenerCarrusel casoDeUsoObtenerCarrusel,
            CasoDeUsoActualizarImagenCarrusel casoDeUsoActualizarImagenCarrusel,
            CasoDeUsoEliminarImagenCarrusel casoDeUsoEliminarImagenCarrusel,
            CasoDeUsoActualizarEnlaceCarrusel casoDeUsoActualizarEnlaceCarrusel) {
        this.casoDeUsoActualizarEnlaceCarrusel = casoDeUsoActualizarEnlaceCarrusel;
        this.casoDeUsoObtenerCarrusel = casoDeUsoObtenerCarrusel;
        this.casoDeUsoActualizarImagenCarrusel = casoDeUsoActualizarImagenCarrusel;
        this.casoDeUsoEliminarImagenCarrusel = casoDeUsoEliminarImagenCarrusel;
    }

    @Override
    public List<ImagenCarrusel> obtenerCarrusel() {
        return casoDeUsoObtenerCarrusel.obtenerCarrusel();
    }

    @Override
    public ImagenCarrusel actualizarImagenCarrusel(Integer espacio, MultipartFile file) throws IOException {
        return casoDeUsoActualizarImagenCarrusel.actualizarImagenCarrusel(espacio, file);
    }

    @Override
    public ImagenCarrusel actualizarEnlaceCarrusel(Integer espacio, String enlace) {
        return casoDeUsoActualizarEnlaceCarrusel.actualizarEnlaceCarrusel(espacio, enlace);
    }

    @Override
    public void eliminarImagenCarrusel(Integer espacio) {
        casoDeUsoEliminarImagenCarrusel.eliminarImagenCarrusel(espacio);
    }
}
