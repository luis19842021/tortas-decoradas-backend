package com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel;

import java.util.List;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoObtenerCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.out.PuertoRepositorioCarrusel;

public class CasoDeUsoObtenerCarruselImpl implements CasoDeUsoObtenerCarrusel {

    private final PuertoRepositorioCarrusel puertoRepositorioCarrusel;

    public CasoDeUsoObtenerCarruselImpl(PuertoRepositorioCarrusel puertoRepositorioCarrusel) {
        this.puertoRepositorioCarrusel = puertoRepositorioCarrusel;
    }

    @Override
    public List<ImagenCarrusel> obtenerCarrusel() {
        return puertoRepositorioCarrusel.obtenerTodas();
    }
}
