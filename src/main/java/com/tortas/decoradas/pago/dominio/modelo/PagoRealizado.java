package com.tortas.decoradas.pago.dominio.modelo;

public record PagoRealizado(String idPago, String estado, Long idPedido, double monto) {
}
