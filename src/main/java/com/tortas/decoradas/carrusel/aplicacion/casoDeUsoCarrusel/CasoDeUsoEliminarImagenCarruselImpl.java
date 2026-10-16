package com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel;

import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoEliminarImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.out.PuertoRepositorioCarrusel;

public class CasoDeUsoEliminarImagenCarruselImpl implements CasoDeUsoEliminarImagenCarrusel {

    private final PuertoRepositorioCarrusel puertoRepositorioCarrusel;

    public CasoDeUsoEliminarImagenCarruselImpl(PuertoRepositorioCarrusel puertoRepositorioCarrusel) {
        this.puertoRepositorioCarrusel = puertoRepositorioCarrusel;
    }

    @Override
    public void eliminarImagenCarrusel(Integer espacio) {
        puertoRepositorioCarrusel.eliminarUrl(espacio);
    }
}
