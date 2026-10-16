package com.tortas.decoradas.factura;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaRepositorioFactura extends JpaRepository<EntidadFactura, Long> {

    Optional<EntidadFactura> findByPedidoId(Long pedidoId);

    boolean existsByPedidoId(Long pedidoId);

    @Query("select f.pedidoId from EntidadFactura f where f.pedidoId in :ids")
    List<Long> pedidosConFactura(@Param("ids") Collection<Long> ids);
}
