package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;

final class CostoDeEnvio {

    private CostoDeEnvio() {
    }

    static long de(Pedido pedido, List<ListaDeCodigosPostales> codigosPostales) {
        if (pedido.getCodigoPostalEnvio() == null) {
            return 0L;
        }
        return codigosPostales.stream()
                .filter(cp -> pedido.getCodigoPostalEnvio().equals(cp.getNumero()))
                .findFirst()
                .map(cp -> cp.getPrecio() == null ? 0L : cp.getPrecio())
                .orElse(0L);
    }
}
