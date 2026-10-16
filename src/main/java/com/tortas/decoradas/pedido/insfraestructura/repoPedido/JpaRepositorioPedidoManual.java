package com.tortas.decoradas.pedido.insfraestructura.repoPedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadPedidoManual;

public interface JpaRepositorioPedidoManual extends JpaRepository<EntidadPedidoManual, Long> {

    List<EntidadPedidoManual> findAllByOrderByFechaAsc();

    @Query("SELECT COALESCE(SUM(p.cantidad), 0) FROM EntidadPedidoManual p WHERE p.fecha = :fecha")
    long sumarCantidadEnFecha(@Param("fecha") LocalDate fecha);

    @Query(value = "SELECT DATE_FORMAT(fecha, '%Y-%m-%d') as fecha, SUM(cantidad) as total FROM pedido_manual GROUP BY fecha", nativeQuery = true)
    List<Map<String, Object>> cantidadManualPorFecha();
}
