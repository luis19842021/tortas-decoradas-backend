package com.tortas.decoradas.pago.dominio.puerto.in;

import com.tortas.decoradas.pago.dominio.modelo.ResultadoConfirmacionPago;

public interface CasoDeUsoConfirmarPago {

    ResultadoConfirmacionPago confirmarDesdeRetorno(String emailUsuario, Long idPedido);

    void confirmarDesdeWebhook(String idPago);
}
