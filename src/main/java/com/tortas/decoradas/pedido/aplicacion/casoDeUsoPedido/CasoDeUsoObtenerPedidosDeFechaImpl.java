package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.tortas.decoradas.pedido.dominio.modelo.ItemPedido;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.modelo.PedidoConCliente;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidosDeFecha;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.producto.dominio.modelo.Producto;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoObtenerPedidosDeFechaImpl implements CasoDeUsoObtenerPedidosDeFecha {

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerPedidosDeFechaImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<PedidoConCliente> obtenerPedidosDeFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("Falta la fecha.");
        }

        List<Pedido> pedidos = puertoRepositorioPedido.BuscarPorFecha(fecha);

        return pedidos.stream().map(pedido -> {
            Usuario usuario = pedido.getId_usuario() == null ? null
                    : puertoRepositorioUsuario.buscarPorId(pedido.getId_usuario()).orElse(null);
            String clienteNombre = usuario == null ? null
                    : ((usuario.getNombre() == null ? "" : usuario.getNombre()) + " "
                            + (usuario.getApellido() == null ? "" : usuario.getApellido())).trim();
            String clienteEmail = usuario == null ? null : usuario.getEmail();
            String clienteTelefono = usuario == null ? null : usuario.getTelefono();

            if (pedido.getItems() != null) {
                for (ItemPedido item : pedido.getItems()) {
                    Producto producto = item.getIdProducto() == null ? null
                            : puertoRepositorioProducto.obtenerProductoPorId(item.getIdProducto()).orElse(null);
                    item.setImagenUrl(producto == null ? null : producto.getUrl_image());
                }
            }

            return new PedidoConCliente(pedido, clienteNombre, clienteEmail, clienteTelefono);
        }).collect(Collectors.toList());
    }
}
