package com.tortas.decoradas.notacredito;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaRepositorioNotaCredito extends JpaRepository<EntidadNotaCredito, Long> {

    Optional<EntidadNotaCredito> findByPedidoId(Long pedidoId);

    boolean existsByPedidoId(Long pedidoId);

    @Query("select f.pedidoId from EntidadNotaCredito f where f.pedidoId in :ids")
    List<Long> pedidosConNotaCredito(@Param("ids") Collection<Long> ids);
}
