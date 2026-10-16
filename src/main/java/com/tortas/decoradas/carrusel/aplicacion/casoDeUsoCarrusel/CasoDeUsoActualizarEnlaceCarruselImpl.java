package com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.in.CasoDeUsoActualizarEnlaceCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.out.PuertoRepositorioCarrusel;

public class CasoDeUsoActualizarEnlaceCarruselImpl implements CasoDeUsoActualizarEnlaceCarrusel {

    private static final int LARGO_MAXIMO = 300;

    private final PuertoRepositorioCarrusel puertoRepositorioCarrusel;

    public CasoDeUsoActualizarEnlaceCarruselImpl(PuertoRepositorioCarrusel puertoRepositorioCarrusel) {
        this.puertoRepositorioCarrusel = puertoRepositorioCarrusel;
    }

    @Override
    public ImagenCarrusel actualizarEnlaceCarrusel(Integer espacio, String enlace) {
        String limpio = enlace == null ? "" : enlace.trim();
        if (limpio.isEmpty()) {
            limpio = null;
        } else if (!esRutaInterna(limpio)) {
            throw new IllegalArgumentException(
                    "El enlace tiene que ser una ruta del sitio, por ejemplo /productos?categoria=TORTAS&tipo=TORTA");
        }

        if (!puertoRepositorioCarrusel.guardarEnlace(espacio, limpio)) {
            throw new IllegalArgumentException("Primero cargá una imagen en ese espacio.");
        }
        return new ImagenCarrusel(espacio, null, limpio);
    }

    private static boolean esRutaInterna(String valor) {
        if (valor.length() > LARGO_MAXIMO || !valor.startsWith("/") || valor.startsWith("//")
                || valor.contains("\\")) {
            return false;
        }
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (Character.isWhitespace(c) || Character.isISOControl(c)) {
                return false;
            }
        }
        return true;
    }
}
