package com.tortas.decoradas.pedido.insfraestructura.repoPedido;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadConfiguracionAgenda;

public interface JpaRepositorioConfiguracionAgenda extends JpaRepository<EntidadConfiguracionAgenda, Long> {
}
