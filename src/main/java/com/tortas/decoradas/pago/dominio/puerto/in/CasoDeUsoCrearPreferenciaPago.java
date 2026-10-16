package com.tortas.decoradas.pago.dominio.puerto.in;

import com.tortas.decoradas.pago.dominio.modelo.PreferenciaPago;

public interface CasoDeUsoCrearPreferenciaPago {

    PreferenciaPago crearPreferenciaPago(String emailUsuario, Long idPedido);
}
