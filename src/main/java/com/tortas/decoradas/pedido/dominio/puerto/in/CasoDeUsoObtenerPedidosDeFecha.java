package com.tortas.decoradas.pedido.dominio.puerto.in;

import java.time.LocalDate;
import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.PedidoConCliente;

public interface CasoDeUsoObtenerPedidosDeFecha {
    List<PedidoConCliente> obtenerPedidosDeFecha(LocalDate fecha);
}
