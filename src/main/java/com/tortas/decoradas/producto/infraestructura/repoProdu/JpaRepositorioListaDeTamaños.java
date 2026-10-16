package com.tortas.decoradas.producto.infraestructura.repoProdu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadListaDeTamaños;

public interface JpaRepositorioListaDeTamaños extends JpaRepository<EntidadListaDeTamaños, Long> {
    @Modifying
    @Query(value = "UPDATE lista_tamanos SET precio = ROUND(precio * ?1 / ?2) * ?2 WHERE precio IS NOT NULL", nativeQuery = true)
    int ajustarPrecios(double factor, long redondeo);
}
