package com.tortas.decoradas.producto.infraestructura.repoProdu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeBizcochuelos;

public interface JpaRepositorioListaDeBizcochuelos extends JpaRepository<EntidadListaDeBizcochuelos, Long> {
    @Modifying
    @Query(value = "UPDATE lista_bizcochuelos SET precio = ROUND(precio * ?1 / ?2) * ?2 WHERE precio IS NOT NULL", nativeQuery = true)
    int ajustarPrecios(double factor, long redondeo);
}
