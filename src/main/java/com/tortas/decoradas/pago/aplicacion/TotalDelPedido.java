package com.tortas.decoradas.pago.aplicacion;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.ItemPedido;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;

final class TotalDelPedido {

    private TotalDelPedido() {
    }

    static long envio(Pedido pedido, List<ListaDeCodigosPostales> codigosPostales) {
        if (pedido.getCodigoPostalEnvio() == null) {
            return 0L;
        }
        return codigosPostales.stream()
                .filter(cp -> pedido.getCodigoPostalEnvio().equals(cp.getNumero()))
                .findFirst()
                .map(cp -> cp.getPrecio() == null ? 0L : cp.getPrecio())
                .orElseThrow(() -> new IllegalArgumentException(
                        "El código postal de envío del pedido ya no está en la lista de envíos."));
    }

    static long productos(Pedido pedido) {
        long total = 0L;
        for (ItemPedido item : pedido.getItems()) {
            total += item.getPrecioUnitario() * item.getCantidad();
        }
        return total;
    }
}
