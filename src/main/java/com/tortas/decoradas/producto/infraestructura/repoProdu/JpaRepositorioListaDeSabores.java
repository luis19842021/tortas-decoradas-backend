package com.tortas.decoradas.producto.infraestructura.repoProdu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeSabores;

public interface JpaRepositorioListaDeSabores extends JpaRepository<EntidadListaDeSabores, Long> {

    @Query("SELECT COUNT(d) FROM EntidadDetalleDeTorta d JOIN d.sabores s WHERE s.idLista_sabor = :id")
    long contarTortasQueUsanSabor(@Param("id") Long id);
}
