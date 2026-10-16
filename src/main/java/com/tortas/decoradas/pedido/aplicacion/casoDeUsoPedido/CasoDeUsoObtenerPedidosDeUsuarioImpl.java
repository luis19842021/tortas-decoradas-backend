package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.util.List;

import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidosDeUsuario;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.producto.dominio.modelo.ListaDeCodigosPostales;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoObtenerPedidosDeUsuarioImpl implements CasoDeUsoObtenerPedidosDeUsuario {

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoRepositorioProducto puertoRepositorioProducto;

    public CasoDeUsoObtenerPedidosDeUsuarioImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoRepositorioProducto puertoRepositorioProducto) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoRepositorioProducto = puertoRepositorioProducto;
    }

    @Override
    public List<Pedido> obtenerPedidosDeUsuario(String emailUsuario) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        List<Pedido> pedidos = puertoRepositorioPedido.BuscarPorUsuario(usuario.getId());
        List<ListaDeCodigosPostales> codigosPostales = puertoRepositorioProducto.obtenerListaDeCodigosPostales();
        for (Pedido pedido : pedidos) {
            if (pedido.getCostoEnvio() == null) {
                pedido.setCostoEnvio(CostoDeEnvio.de(pedido, codigosPostales));
            }
        }
        return pedidos;
    }
}
