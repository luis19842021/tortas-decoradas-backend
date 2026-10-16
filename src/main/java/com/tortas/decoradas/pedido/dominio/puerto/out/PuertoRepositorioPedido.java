package com.tortas.decoradas.pedido.dominio.puerto.out;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.EstadoPreparacion;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;
import java.time.LocalDateTime;

public interface PuertoRepositorioPedido {

    List<Pedido> BuscarTodosLosPedido();
   List<Map<String,Object>> BuscarCantidadDePedidosPorFechas();
   List<Pedido> BuscarPorFecha(LocalDate fecha);
   long ContarPedidosEnFecha(LocalDate fecha);
   ConfiguracionAgenda ObtenerConfiguracionAgenda();
   ConfiguracionAgenda ActualizarCupoMaximoPorDia(int nuevoCupo);
   Pedido GuardarPedido(Pedido pedido);
   Pedido BuscarPorId(Long idPedido);
   Pedido ActualizarEstado(Long idPedido, Estado nuevoEstado);
   Pedido GuardarCostoEnvio(Long idPedido, long costoEnvio);
   List<Pedido> BuscarPorUsuario(Long idUsuario);
   Pedido ActualizarEstadoPreparacion(Long idPedido, EstadoPreparacion nuevoEstadoPreparacion);
   int IniciarCoccionAutomatica(LocalDate limite);
   Pedido ReprogramarPedido(Long idPedido, LocalDate nuevaFecha);
   Pedido CancelarPedido(Long idPedido, long montoADevolver, int porcentajeDevuelto);

   List<DiaBloqueado> ObtenerDiasBloqueados();
   DiaBloqueado BloquearDia(LocalDate fecha, String motivo);
   void DesbloquearDia(LocalDate fecha);
   boolean EstaDiaBloqueado(LocalDate fecha);

   List<PedidoManual> ObtenerPedidosManuales();
   PedidoManual CrearPedidoManual(LocalDate fecha, int cantidad, String motivo);
   void EliminarPedidoManual(Long id);
   boolean ExistePedidoManual(Long id);

   int CancelarPedidosSinPagar(LocalDateTime limite);
}
