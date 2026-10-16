package com.tortas.decoradas.producto.infraestructura.repoProdu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadCategoriaSabor;

public interface JpaRepositorioCategoriaSabor extends JpaRepository<EntidadCategoriaSabor, Long> {
    @Modifying
    @Query(value = "UPDATE categoria_sabor SET precio = ROUND(precio * ?1 / ?2) * ?2 WHERE precio IS NOT NULL", nativeQuery = true)
    int ajustarPrecios(double factor, long redondeo);
}
