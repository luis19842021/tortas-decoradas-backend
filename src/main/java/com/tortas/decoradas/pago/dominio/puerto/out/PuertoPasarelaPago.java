package com.tortas.decoradas.pago.dominio.puerto.out;

import java.util.List;
import java.util.Optional;

import com.tortas.decoradas.pago.dominio.modelo.LineaPago;
import com.tortas.decoradas.pago.dominio.modelo.PagoRealizado;
import com.tortas.decoradas.pago.dominio.modelo.PreferenciaPago;

public interface PuertoPasarelaPago {

    PreferenciaPago crearPreferencia(Long idPedido, List<LineaPago> lineas);

    Optional<PagoRealizado> obtenerPago(String idPago);

    List<PagoRealizado> buscarPagosDePedido(Long idPedido);
}
