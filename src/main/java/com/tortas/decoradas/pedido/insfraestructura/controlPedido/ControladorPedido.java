package com.tortas.decoradas.pedido.insfraestructura.controlPedido;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortas.decoradas.pedido.aplicacion.servicio.ServicioPedido;
import com.tortas.decoradas.pedido.dominio.modelo.ActualizarCupoMaximoRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.ActualizarEstadoPedidoRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.BloquearDiaRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.modelo.CrearPedidoManualRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.CrearPedidoRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.modelo.PedidoConCliente;
import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;
import com.tortas.decoradas.pedido.dominio.modelo.ReprogramarPedidoRequestDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class ControladorPedido {

    private final ServicioPedido servicioPedido;

    public ControladorPedido(ServicioPedido servicioPedido) {
        this.servicioPedido = servicioPedido;
    }

    @GetMapping("pedidos")
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        List<Pedido> pedidos = servicioPedido.obtenerTodosLosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("pedidosPor")
    public ResponseEntity<List<Map<String, Object>>> obtenerPedidosPorFecha() {
        List<Map<String, Object>> pedidos = servicioPedido.obtenerCantidadDePedidosPorFechas();

        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("pedidos/mios")
    public ResponseEntity<List<Pedido>> obtenerMisPedidos(Authentication authentication) {
        List<Pedido> pedidos = servicioPedido.obtenerPedidosDeUsuario(authentication.getName());
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping("pedidos")
    public ResponseEntity<Pedido> crearPedido(@RequestBody CrearPedidoRequestDto datos,
            Authentication authentication) {
        Pedido pedido = servicioPedido.crearPedido(authentication.getName(), datos);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PatchMapping("pedidos/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstadoPedido(@PathVariable Long id,
            @RequestBody ActualizarEstadoPedidoRequestDto datos, Authentication authentication) {
        Pedido pedido = servicioPedido.actualizarEstado(authentication.getName(), id, datos.getEstado());
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PatchMapping("pedidos/{id}/entregar")
    public ResponseEntity<Pedido> marcarEntregado(@PathVariable Long id) {
        try {
            Pedido pedido = servicioPedido.marcarEntregado(id);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("pedidos/{id}/reprogramar")
    public ResponseEntity<Pedido> reprogramarPedido(@PathVariable Long id,
            @RequestBody ReprogramarPedidoRequestDto datos, Authentication authentication) {
        Pedido pedido = servicioPedido.reprogramar(authentication.getName(), id, datos.getNuevaFecha());
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PatchMapping("pedidos/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id, Authentication authentication) {
        Pedido pedido = servicioPedido.cancelar(authentication.getName(), id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @GetMapping("configuracion/cupoMaximoPorDia")
    public ResponseEntity<ConfiguracionAgenda> obtenerConfiguracionAgenda() {
        ConfiguracionAgenda configuracion = servicioPedido.obtenerConfiguracionAgenda();
        return new ResponseEntity<>(configuracion, HttpStatus.OK);
    }

    @PutMapping("configuracion/cupoMaximoPorDia")
    public ResponseEntity<ConfiguracionAgenda> actualizarCupoMaximoPorDia(
            @RequestBody ActualizarCupoMaximoRequestDto datos) {
        ConfiguracionAgenda configuracion = servicioPedido.actualizarCupoMaximoPorDia(datos.getCupoMaximoPorDia());
        return new ResponseEntity<>(configuracion, HttpStatus.OK);
    }

    @GetMapping("configuracion/diasBloqueados")
    public ResponseEntity<List<DiaBloqueado>> obtenerDiasBloqueados() {
        List<DiaBloqueado> dias = servicioPedido.obtenerDiasBloqueados();
        return new ResponseEntity<>(dias, HttpStatus.OK);
    }

    @PostMapping("configuracion/diasBloqueados")
    public ResponseEntity<DiaBloqueado> bloquearDia(@RequestBody BloquearDiaRequestDto datos) {
        DiaBloqueado dia = servicioPedido.bloquearDia(datos.getFecha(), datos.getMotivo());
        return new ResponseEntity<>(dia, HttpStatus.CREATED);
    }

    @DeleteMapping("configuracion/diasBloqueados/{fecha}")
    public ResponseEntity<Void> desbloquearDia(@PathVariable String fecha) {
        servicioPedido.desbloquearDia(LocalDate.parse(fecha));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("configuracion/pedidosManuales")
    public ResponseEntity<List<PedidoManual>> obtenerPedidosManuales() {
        List<PedidoManual> pedidos = servicioPedido.obtenerPedidosManuales();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping("configuracion/pedidosManuales")
    public ResponseEntity<PedidoManual> crearPedidoManual(@RequestBody CrearPedidoManualRequestDto datos) {
        PedidoManual pedido = servicioPedido.crearPedidoManual(datos.getFecha(), datos.getCantidad(), datos.getMotivo());
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @DeleteMapping("configuracion/pedidosManuales/{id}")
    public ResponseEntity<Void> eliminarPedidoManual(@PathVariable Long id) {
        servicioPedido.eliminarPedidoManual(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("pedidos/porFecha/{fecha}")
    public ResponseEntity<List<PedidoConCliente>> obtenerPedidosDeFecha(@PathVariable String fecha) {
        List<PedidoConCliente> pedidos = servicioPedido.obtenerPedidosDeFecha(LocalDate.parse(fecha));
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

}
