package com.tortas.decoradas.carrusel.infraestructura.repoCarrusel;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.carrusel.infraestructura.entiCarrusel.EntidadCarruselImagen;

public interface JpaRepositorioCarrusel extends JpaRepository<EntidadCarruselImagen, Integer> {
}
