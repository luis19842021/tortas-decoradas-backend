package com.tortas.decoradas.pedido.aplicacion.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.modelo.CrearPedidoRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.modelo.PedidoConCliente;
import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoActualizarCupoMaximoPorDia;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoActualizarEstadoPedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoBloquearDia;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCancelarPedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCrearPedido;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCrearPedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoDesbloquearDia;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoEliminarPedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoIniciarCoccionAutomatica;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoMarcarEntregado;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerCantidadDePedidosPorFechas;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerDiasBloqueados;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidos;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidosDeFecha;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidosDeUsuario;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoObtenerPedidosManuales;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoReprogramarPedido;

public class ServicioPedido implements CasoDeUsoObtenerPedidos, CasoDeUsoObtenerCantidadDePedidosPorFechas,
        CasoDeUsoCrearPedido, CasoDeUsoActualizarEstadoPedido, CasoDeUsoObtenerPedidosDeUsuario,
        CasoDeUsoMarcarEntregado, CasoDeUsoIniciarCoccionAutomatica, CasoDeUsoReprogramarPedido,
        CasoDeUsoCancelarPedido, CasoDeUsoObtenerConfiguracionAgenda, CasoDeUsoActualizarCupoMaximoPorDia,
        CasoDeUsoObtenerDiasBloqueados, CasoDeUsoBloquearDia, CasoDeUsoDesbloquearDia,
        CasoDeUsoCrearPedidoManual, CasoDeUsoObtenerPedidosManuales, CasoDeUsoEliminarPedidoManual,
        CasoDeUsoObtenerPedidosDeFecha {

    private final CasoDeUsoObtenerPedidos casoDeUsoObtenerPedidos;
    private final CasoDeUsoObtenerCantidadDePedidosPorFechas cantidadDePedidosPorFechas;
    private final CasoDeUsoCrearPedido casoDeUsoCrearPedido;
    private final CasoDeUsoActualizarEstadoPedido casoDeUsoActualizarEstadoPedido;
    private final CasoDeUsoObtenerPedidosDeUsuario casoDeUsoObtenerPedidosDeUsuario;
    private final CasoDeUsoMarcarEntregado casoDeUsoMarcarEntregado;
    private final CasoDeUsoIniciarCoccionAutomatica casoDeUsoIniciarCoccionAutomatica;
    private final CasoDeUsoReprogramarPedido casoDeUsoReprogramarPedido;
    private final CasoDeUsoCancelarPedido casoDeUsoCancelarPedido;
    private final CasoDeUsoObtenerConfiguracionAgenda casoDeUsoObtenerConfiguracionAgenda;
    private final CasoDeUsoActualizarCupoMaximoPorDia casoDeUsoActualizarCupoMaximoPorDia;
    private final CasoDeUsoObtenerDiasBloqueados casoDeUsoObtenerDiasBloqueados;
    private final CasoDeUsoBloquearDia casoDeUsoBloquearDia;
    private final CasoDeUsoDesbloquearDia casoDeUsoDesbloquearDia;
    private final CasoDeUsoCrearPedidoManual casoDeUsoCrearPedidoManual;
    private final CasoDeUsoObtenerPedidosManuales casoDeUsoObtenerPedidosManuales;
    private final CasoDeUsoEliminarPedidoManual casoDeUsoEliminarPedidoManual;
    private final CasoDeUsoObtenerPedidosDeFecha casoDeUsoObtenerPedidosDeFecha;

    public ServicioPedido(CasoDeUsoObtenerPedidos casoDeUsoObtenerPedidos,
            CasoDeUsoObtenerCantidadDePedidosPorFechas cantidadDePedidosPorFechas,
            CasoDeUsoCrearPedido casoDeUsoCrearPedido,
            CasoDeUsoActualizarEstadoPedido casoDeUsoActualizarEstadoPedido,
            CasoDeUsoObtenerPedidosDeUsuario casoDeUsoObtenerPedidosDeUsuario,
            CasoDeUsoMarcarEntregado casoDeUsoMarcarEntregado,
            CasoDeUsoIniciarCoccionAutomatica casoDeUsoIniciarCoccionAutomatica,
            CasoDeUsoReprogramarPedido casoDeUsoReprogramarPedido,
            CasoDeUsoCancelarPedido casoDeUsoCancelarPedido,
            CasoDeUsoObtenerConfiguracionAgenda casoDeUsoObtenerConfiguracionAgenda,
            CasoDeUsoActualizarCupoMaximoPorDia casoDeUsoActualizarCupoMaximoPorDia,
            CasoDeUsoObtenerDiasBloqueados casoDeUsoObtenerDiasBloqueados,
            CasoDeUsoBloquearDia casoDeUsoBloquearDia,
            CasoDeUsoDesbloquearDia casoDeUsoDesbloquearDia,
            CasoDeUsoCrearPedidoManual casoDeUsoCrearPedidoManual,
            CasoDeUsoObtenerPedidosManuales casoDeUsoObtenerPedidosManuales,
            CasoDeUsoEliminarPedidoManual casoDeUsoEliminarPedidoManual,
            CasoDeUsoObtenerPedidosDeFecha casoDeUsoObtenerPedidosDeFecha) {
        this.casoDeUsoObtenerPedidos = casoDeUsoObtenerPedidos;
        this.cantidadDePedidosPorFechas = cantidadDePedidosPorFechas;
        this.casoDeUsoCrearPedido = casoDeUsoCrearPedido;
        this.casoDeUsoActualizarEstadoPedido = casoDeUsoActualizarEstadoPedido;
        this.casoDeUsoObtenerPedidosDeUsuario = casoDeUsoObtenerPedidosDeUsuario;
        this.casoDeUsoMarcarEntregado = casoDeUsoMarcarEntregado;
        this.casoDeUsoIniciarCoccionAutomatica = casoDeUsoIniciarCoccionAutomatica;
        this.casoDeUsoReprogramarPedido = casoDeUsoReprogramarPedido;
        this.casoDeUsoCancelarPedido = casoDeUsoCancelarPedido;
        this.casoDeUsoObtenerConfiguracionAgenda = casoDeUsoObtenerConfiguracionAgenda;
        this.casoDeUsoActualizarCupoMaximoPorDia = casoDeUsoActualizarCupoMaximoPorDia;
        this.casoDeUsoObtenerDiasBloqueados = casoDeUsoObtenerDiasBloqueados;
        this.casoDeUsoBloquearDia = casoDeUsoBloquearDia;
        this.casoDeUsoDesbloquearDia = casoDeUsoDesbloquearDia;
        this.casoDeUsoCrearPedidoManual = casoDeUsoCrearPedidoManual;
        this.casoDeUsoObtenerPedidosManuales = casoDeUsoObtenerPedidosManuales;
        this.casoDeUsoEliminarPedidoManual = casoDeUsoEliminarPedidoManual;
        this.casoDeUsoObtenerPedidosDeFecha = casoDeUsoObtenerPedidosDeFecha;
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return casoDeUsoObtenerPedidos.obtenerTodosLosPedidos();
    }

    @Override
    public List<Map<String,Object>> obtenerCantidadDePedidosPorFechas() {
        return cantidadDePedidosPorFechas.obtenerCantidadDePedidosPorFechas();

    }

    @Override
    public Pedido crearPedido(String emailUsuario, CrearPedidoRequestDto datos) {
        return casoDeUsoCrearPedido.crearPedido(emailUsuario, datos);
    }

    @Override
    public Pedido actualizarEstado(String emailUsuario, Long idPedido, Estado nuevoEstado) {
        return casoDeUsoActualizarEstadoPedido.actualizarEstado(emailUsuario, idPedido, nuevoEstado);
    }

    @Override
    public List<Pedido> obtenerPedidosDeUsuario(String emailUsuario) {
        return casoDeUsoObtenerPedidosDeUsuario.obtenerPedidosDeUsuario(emailUsuario);
    }

    @Override
    public Pedido marcarEntregado(Long idPedido) {
        return casoDeUsoMarcarEntregado.marcarEntregado(idPedido);
    }

    @Override
    public int iniciarCoccionParaPedidosProximos() {
        return casoDeUsoIniciarCoccionAutomatica.iniciarCoccionParaPedidosProximos();
    }

    @Override
    public Pedido reprogramar(String emailUsuario, Long idPedido, LocalDate nuevaFecha) {
        return casoDeUsoReprogramarPedido.reprogramar(emailUsuario, idPedido, nuevaFecha);
    }

    @Override
    public Pedido cancelar(String emailUsuario, Long idPedido) {
        return casoDeUsoCancelarPedido.cancelar(emailUsuario, idPedido);
    }

    @Override
    public ConfiguracionAgenda obtenerConfiguracionAgenda() {
        return casoDeUsoObtenerConfiguracionAgenda.obtenerConfiguracionAgenda();
    }

    @Override
    public ConfiguracionAgenda actualizarCupoMaximoPorDia(int nuevoCupo) {
        return casoDeUsoActualizarCupoMaximoPorDia.actualizarCupoMaximoPorDia(nuevoCupo);
    }

    @Override
    public List<DiaBloqueado> obtenerDiasBloqueados() {
        return casoDeUsoObtenerDiasBloqueados.obtenerDiasBloqueados();
    }

    @Override
    public DiaBloqueado bloquearDia(LocalDate fecha, String motivo) {
        return casoDeUsoBloquearDia.bloquearDia(fecha, motivo);
    }

    @Override
    public void desbloquearDia(LocalDate fecha) {
        casoDeUsoDesbloquearDia.desbloquearDia(fecha);
    }

    @Override
    public PedidoManual crearPedidoManual(LocalDate fecha, int cantidad, String motivo) {
        return casoDeUsoCrearPedidoManual.crearPedidoManual(fecha, cantidad, motivo);
    }

    @Override
    public List<PedidoManual> obtenerPedidosManuales() {
        return casoDeUsoObtenerPedidosManuales.obtenerPedidosManuales();
    }

    @Override
    public void eliminarPedidoManual(Long id) {
        casoDeUsoEliminarPedidoManual.eliminarPedidoManual(id);
    }

    @Override
    public List<PedidoConCliente> obtenerPedidosDeFecha(LocalDate fecha) {
        return casoDeUsoObtenerPedidosDeFecha.obtenerPedidosDeFecha(fecha);
    }

}
