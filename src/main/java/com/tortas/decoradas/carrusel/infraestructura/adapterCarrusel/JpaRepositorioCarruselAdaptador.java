package com.tortas.decoradas.carrusel.infraestructura.adapterCarrusel;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.out.PuertoRepositorioCarrusel;
import com.tortas.decoradas.carrusel.infraestructura.entiCarrusel.EntidadCarruselImagen;
import com.tortas.decoradas.carrusel.infraestructura.repoCarrusel.JpaRepositorioCarrusel;

@Component
public class JpaRepositorioCarruselAdaptador implements PuertoRepositorioCarrusel {

    private final JpaRepositorioCarrusel jpaRepositorioCarrusel;

    public JpaRepositorioCarruselAdaptador(JpaRepositorioCarrusel jpaRepositorioCarrusel) {
        this.jpaRepositorioCarrusel = jpaRepositorioCarrusel;
    }

    @Override
    public List<ImagenCarrusel> obtenerTodas() {
        return jpaRepositorioCarrusel.findAll().stream().map(EntidadCarruselImagen::toDomainModel).toList();
    }

    @Override
    public void guardarUrl(Integer espacio, String url) {
        EntidadCarruselImagen entidad = jpaRepositorioCarrusel.findById(espacio)
                .orElseGet(() -> new EntidadCarruselImagen(espacio, url));
        entidad.setUrl(url);
        jpaRepositorioCarrusel.save(entidad);
    }

    @Override
    public boolean guardarEnlace(Integer espacio, String enlace) {
        return jpaRepositorioCarrusel.findById(espacio).map(entidad -> {
            entidad.setEnlace(enlace);
            jpaRepositorioCarrusel.save(entidad);
            return true;
        }).orElse(false);
    }

    @Override
    public void eliminarUrl(Integer espacio) {
        if (jpaRepositorioCarrusel.existsById(espacio)) {
            jpaRepositorioCarrusel.deleteById(espacio);
        }
    }
}
