package com.tortas.decoradas.pedido.insfraestructura.configPedido;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoActualizarCupoMaximoPorDiaImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoActualizarEstadoPedidoImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoBloquearDiaImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoCancelarPedidoImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoCrearPedidoImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoCrearPedidoManualImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoDesbloquearDiaImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoEliminarPedidoManualImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoIniciarCoccionAutomaticaImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoMarcarEntregadoImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoObtenerCantidadDePedidosPorFechaImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoObtenerConfiguracionAgendaImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoObtenerDiasBloqueadosImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoObtenerPedidoImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoObtenerPedidosDeFechaImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoObtenerPedidosDeUsuarioImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoObtenerPedidosManualesImpl;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoReprogramarPedidoImpl;
import com.tortas.decoradas.pedido.aplicacion.servicio.ServicioPedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;
import com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido.CasoDeUsoCancelarPedidosSinPagarImpl;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCancelarPedidosSinPagar;

@Configuration
public class ConfigAppPedido {

    @Bean
    public ServicioPedido servicioPedido(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoNotificador puertoNotificador,
            PuertoRepositorioProducto puertoRepositorioProducto) {
        return new ServicioPedido(
                new CasoDeUsoObtenerPedidoImpl(puertoRepositorioPedido),
                new CasoDeUsoObtenerCantidadDePedidosPorFechaImpl(puertoRepositorioPedido),
                new CasoDeUsoCrearPedidoImpl(puertoRepositorioPedido, puertoRepositorioUsuario, puertoNotificador),
                new CasoDeUsoActualizarEstadoPedidoImpl(puertoRepositorioPedido, puertoRepositorioUsuario, puertoNotificador),
                new CasoDeUsoObtenerPedidosDeUsuarioImpl(puertoRepositorioPedido, puertoRepositorioUsuario,
                        puertoRepositorioProducto),
                new CasoDeUsoMarcarEntregadoImpl(puertoRepositorioPedido),
                new CasoDeUsoIniciarCoccionAutomaticaImpl(puertoRepositorioPedido),
                new CasoDeUsoReprogramarPedidoImpl(puertoRepositorioPedido, puertoRepositorioUsuario, puertoNotificador),
                new CasoDeUsoCancelarPedidoImpl(puertoRepositorioPedido, puertoRepositorioUsuario, puertoNotificador,
                        puertoRepositorioProducto),
                new CasoDeUsoObtenerConfiguracionAgendaImpl(puertoRepositorioPedido),
                new CasoDeUsoActualizarCupoMaximoPorDiaImpl(puertoRepositorioPedido),
                new CasoDeUsoObtenerDiasBloqueadosImpl(puertoRepositorioPedido),
                new CasoDeUsoBloquearDiaImpl(puertoRepositorioPedido),
                new CasoDeUsoDesbloquearDiaImpl(puertoRepositorioPedido),
                new CasoDeUsoCrearPedidoManualImpl(puertoRepositorioPedido),
                new CasoDeUsoObtenerPedidosManualesImpl(puertoRepositorioPedido),
                new CasoDeUsoEliminarPedidoManualImpl(puertoRepositorioPedido),
                new CasoDeUsoObtenerPedidosDeFechaImpl(puertoRepositorioPedido, puertoRepositorioUsuario,
                        puertoRepositorioProducto));

    }

    @Bean
    public CasoDeUsoCancelarPedidosSinPagar casoDeUsoCancelarPedidosSinPagar(
            PuertoRepositorioPedido puertoRepositorioPedido) {
        return new CasoDeUsoCancelarPedidosSinPagarImpl(puertoRepositorioPedido);
    }

}
