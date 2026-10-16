package com.tortas.decoradas.pedido.insfraestructura.repoPedido;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadDiaBloqueado;

public interface JpaRepositorioDiaBloqueado extends JpaRepository<EntidadDiaBloqueado, Long> {

    List<EntidadDiaBloqueado> findAllByOrderByFechaAsc();

    boolean existsByFecha(LocalDate fecha);

    @Modifying
    @Transactional
    void deleteByFecha(LocalDate fecha);
}
