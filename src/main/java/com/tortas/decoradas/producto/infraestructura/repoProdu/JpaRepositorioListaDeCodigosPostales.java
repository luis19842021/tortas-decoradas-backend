package com.tortas.decoradas.producto.infraestructura.repoProdu;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeCodigosPostales;

public interface JpaRepositorioListaDeCodigosPostales extends JpaRepository<EntidadListaDeCodigosPostales, Long> {
}
