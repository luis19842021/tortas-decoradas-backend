package com.tortas.decoradas.producto.infraestructura.repoProdu;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeSabores;

public interface JpaRepositorioListaDeSabores extends JpaRepository<EntidadListaDeSabores, Long> {
}
