package com.tortas.decoradas.pago.aplicacion;

import java.util.ArrayList;
import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.ItemPedido;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.pago.dominio.modelo.LineaPago;
import com.tortas.decoradas.pago.dominio.modelo.PreferenciaPago;
import com.tortas.decoradas.pago.dominio.puerto.in.CasoDeUsoCrearPreferenciaPago;
import com.tortas.decoradas.pago.dominio.puerto.out.PuertoPasarelaPago;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoCrearPreferenciaPagoImpl implements CasoDeUsoCrearPreferenciaPago {

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoRepositorioProducto puertoRepositorioProducto;
    private final PuertoPasarelaPago puertoPasarelaPago;

    public CasoDeUsoCrearPreferenciaPagoImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoRepositorioProducto puertoRepositorioProducto,
            PuertoPasarelaPago puertoPasarelaPago) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoRepositorioProducto = puertoRepositorioProducto;
        this.puertoPasarelaPago = puertoPasarelaPago;
    }

    @Override
    public PreferenciaPago crearPreferenciaPago(String emailUsuario, Long idPedido) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        Pedido pedido = puertoRepositorioPedido.BuscarPorId(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }
        if (!pedido.getId_usuario().equals(usuario.getId())) {
            throw new IllegalArgumentException("No tenés permiso sobre este pedido.");
        }
        if (pedido.getEstado() != Estado.INICIADO) {
            throw new IllegalArgumentException("El pedido ya está " + pedido.getEstado() + ".");
        }

        List<LineaPago> lineas = new ArrayList<>();
        for (ItemPedido item : pedido.getItems()) {
            String titulo = item.getDescripcion() == null || item.getDescripcion().isBlank()
                    ? "Producto"
                    : item.getDescripcion();
            if (titulo.length() > 120) {
                titulo = titulo.substring(0, 117) + "...";
            }
            lineas.add(new LineaPago(titulo, item.getCantidad(), item.getPrecioUnitario()));
        }
        long envio = TotalDelPedido.envio(pedido, puertoRepositorioProducto.obtenerListaDeCodigosPostales());
        if (envio > 0) {
            lineas.add(new LineaPago("Envío a domicilio", 1, envio));
        }

        return puertoPasarelaPago.crearPreferencia(idPedido, lineas);
    }
}
