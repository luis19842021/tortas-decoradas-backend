package com.tortas.decoradas.pedido.insfraestructura.repoPedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadPedido;
import java.time.LocalDateTime;

public interface JpaRepositorioPedido extends JpaRepository<EntidadPedido, Long> {

    @Query(value = "SELECT DATE_FORMAT(fecha_de_pedido, '%Y-%m-%d') as fecha_de_pedido, COUNT(*) as total FROM pedido WHERE estado <> 'CANCELADO' GROUP BY fecha_de_pedido", nativeQuery = true)
    List<Map<String,Object>> cantidadDePedidosPorFecha();

    @Query("SELECT COUNT(p) FROM EntidadPedido p WHERE p.fecha_de_pedido = :fecha AND p.estado <> com.tortas.decoradas.pedido.dominio.modelo.Estado.CANCELADO")
    long contarPedidosEnFecha(@Param("fecha") LocalDate fecha);

    @Query("SELECT p FROM EntidadPedido p WHERE p.id.user_id = :idUsuario ORDER BY p.fecha_de_pedido DESC, p.idpedido DESC")
    List<EntidadPedido> buscarPorUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT p FROM EntidadPedido p WHERE p.fecha_de_pedido = :fecha ORDER BY p.idpedido DESC")
    List<EntidadPedido> buscarPorFecha(@Param("fecha") LocalDate fecha);

    @Query("SELECT p FROM EntidadPedido p WHERE p.estado = com.tortas.decoradas.pedido.dominio.modelo.Estado.CUMPLIDO "
            + "AND p.estadoPreparacion = com.tortas.decoradas.pedido.dominio.modelo.EstadoPreparacion.DISENANDO "
            + "AND p.fecha_de_pedido <= :limite")
    List<EntidadPedido> buscarParaIniciarCoccion(@Param("limite") LocalDate limite);

    @Query("SELECT p FROM EntidadPedido p WHERE p.estado = com.tortas.decoradas.pedido.dominio.modelo.Estado.INICIADO "
            + "AND p.fechaCreacion IS NOT NULL AND p.fechaCreacion < :limite")
    List<EntidadPedido> buscarSinPagarCreadosAntesDe(@Param("limite") LocalDateTime limite);
}
