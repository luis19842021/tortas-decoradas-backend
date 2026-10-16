package com.tortas.decoradas.pedido.insfraestructura.adapterPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.modelo.DiaBloqueado;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.EstadoPreparacion;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.modelo.PedidoManual;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadConfiguracionAgenda;
import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadDiaBloqueado;
import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadItemPedido;
import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadPedido;
import com.tortas.decoradas.pedido.insfraestructura.entiPedido.EntidadPedidoManual;
import com.tortas.decoradas.pedido.insfraestructura.repoPedido.JpaRepositorioConfiguracionAgenda;
import com.tortas.decoradas.pedido.insfraestructura.repoPedido.JpaRepositorioDiaBloqueado;
import com.tortas.decoradas.pedido.insfraestructura.repoPedido.JpaRepositorioPedido;
import com.tortas.decoradas.pedido.insfraestructura.repoPedido.JpaRepositorioPedidoManual;
import com.tortas.decoradas.producto.infraestructura.entiProdu.EntidadProducto;
import com.tortas.decoradas.producto.infraestructura.repoProdu.JpaRepositorioProducto;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;
import com.tortas.decoradas.usuario.infraestructura.repoUser.JpaRepositorioUsuario;
import java.time.LocalDateTime;

@Component
public class JpaRepositorioPedidoAdaptador implements PuertoRepositorioPedido {

    private final JpaRepositorioPedido jpaRepositorioPedido;
    private final JpaRepositorioUsuario jpaRepositorioUsuario;
    private final JpaRepositorioProducto jpaRepositorioProducto;
    private final JpaRepositorioConfiguracionAgenda jpaRepositorioConfiguracionAgenda;
    private final JpaRepositorioDiaBloqueado jpaRepositorioDiaBloqueado;
    private final JpaRepositorioPedidoManual jpaRepositorioPedidoManual;

    private static final Long ID_CONFIGURACION_AGENDA = 1L;
    private static final int CUPO_MAXIMO_POR_DIA_POR_DEFECTO = 3;

    public JpaRepositorioPedidoAdaptador(JpaRepositorioPedido jpaRepositorioPedido,
            JpaRepositorioUsuario jpaRepositorioUsuario,
            JpaRepositorioProducto jpaRepositorioProducto,
            JpaRepositorioConfiguracionAgenda jpaRepositorioConfiguracionAgenda,
            JpaRepositorioDiaBloqueado jpaRepositorioDiaBloqueado,
            JpaRepositorioPedidoManual jpaRepositorioPedidoManual) {
        this.jpaRepositorioPedido = jpaRepositorioPedido;
        this.jpaRepositorioUsuario = jpaRepositorioUsuario;
        this.jpaRepositorioProducto = jpaRepositorioProducto;
        this.jpaRepositorioConfiguracionAgenda = jpaRepositorioConfiguracionAgenda;
        this.jpaRepositorioDiaBloqueado = jpaRepositorioDiaBloqueado;
        this.jpaRepositorioPedidoManual = jpaRepositorioPedidoManual;
    }

    @Override
    public List<Pedido> BuscarTodosLosPedido() {
      return jpaRepositorioPedido.findAll().stream().map(EntidadPedido::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> BuscarPorFecha(LocalDate fecha) {
        return jpaRepositorioPedido.buscarPorFecha(fecha).stream()
                .map(EntidadPedido::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String,Object>> BuscarCantidadDePedidosPorFechas() {
        Map<String, Long> totalesPorFecha = new LinkedHashMap<>();

        for (Map<String, Object> fila : jpaRepositorioPedido.cantidadDePedidosPorFecha()) {
            String fecha = String.valueOf(fila.get("fecha_de_pedido"));
            long total = ((Number) fila.get("total")).longValue();
            totalesPorFecha.merge(fecha, total, Long::sum);
        }
        for (Map<String, Object> fila : jpaRepositorioPedidoManual.cantidadManualPorFecha()) {
            String fecha = String.valueOf(fila.get("fecha"));
            long total = ((Number) fila.get("total")).longValue();
            totalesPorFecha.merge(fecha, total, Long::sum);
        }

        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Map.Entry<String, Long> entrada : totalesPorFecha.entrySet()) {
            resultado.add(Map.of("fecha_de_pedido", entrada.getKey(), "total", entrada.getValue()));
        }
        return resultado;
    }

    @Override
    public Pedido GuardarPedido(Pedido pedido) {
        EntidadPedido entidadPedido = EntidadPedido.fromDomainModel(pedido);

        EntidadUsuario usuario = jpaRepositorioUsuario.getReferenceById(pedido.getId_usuario());
        entidadPedido.setId(usuario);

        List<EntidadItemPedido> entidadItems = pedido.getItems().stream().map(item -> {
            EntidadItemPedido entidadItem = EntidadItemPedido.fromDomainModel(item);
            entidadItem.setPedido(entidadPedido);

            EntidadProducto producto = jpaRepositorioProducto.getReferenceById(item.getIdProducto());
            entidadItem.setProducto(producto);

            return entidadItem;
        }).collect(Collectors.toList());
        entidadPedido.setItems(entidadItems);

        EntidadPedido guardado = jpaRepositorioPedido.save(entidadPedido);
        return guardado.toDomainModel();
    }

    @Override
    public Pedido GuardarCostoEnvio(Long idPedido, long costoEnvio) {
        EntidadPedido entidadPedido = jpaRepositorioPedido.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado."));

        entidadPedido.setCostoEnvio(costoEnvio);
        return jpaRepositorioPedido.save(entidadPedido).toDomainModel();
    }

    @Override
    public Pedido BuscarPorId(Long idPedido) {
        return jpaRepositorioPedido.findById(idPedido)
                .map(EntidadPedido::toDomainModel)
                .orElse(null);
    }

    @Override
    public Pedido ActualizarEstado(Long idPedido, Estado nuevoEstado) {
        EntidadPedido entidadPedido = jpaRepositorioPedido.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado."));

        entidadPedido.setEstado(nuevoEstado);
        if (nuevoEstado == Estado.CUMPLIDO) {
            entidadPedido.setEstadoPreparacion(EstadoPreparacion.DISENANDO);
        }

        EntidadPedido guardado = jpaRepositorioPedido.save(entidadPedido);
        return guardado.toDomainModel();
    }

    @Override
    public Pedido ActualizarEstadoPreparacion(Long idPedido, EstadoPreparacion nuevoEstadoPreparacion) {
        EntidadPedido entidadPedido = jpaRepositorioPedido.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado."));

        entidadPedido.setEstadoPreparacion(nuevoEstadoPreparacion);

        EntidadPedido guardado = jpaRepositorioPedido.save(entidadPedido);
        return guardado.toDomainModel();
    }

    @Override
    public int IniciarCoccionAutomatica(LocalDate limite) {
        List<EntidadPedido> pedidos = jpaRepositorioPedido.buscarParaIniciarCoccion(limite);
        pedidos.forEach(p -> p.setEstadoPreparacion(EstadoPreparacion.COCINANDO));
        jpaRepositorioPedido.saveAll(pedidos);
        return pedidos.size();
    }

    @Override
    public int CancelarPedidosSinPagar(LocalDateTime limite) {
        List<EntidadPedido> pedidos = jpaRepositorioPedido.buscarSinPagarCreadosAntesDe(limite);
        pedidos.forEach(p -> p.setEstado(Estado.CANCELADO));
        jpaRepositorioPedido.saveAll(pedidos);
        return pedidos.size();
    }

    @Override
    public List<Pedido> BuscarPorUsuario(Long idUsuario) {
        return jpaRepositorioPedido.buscarPorUsuario(idUsuario).stream()
                .map(EntidadPedido::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Pedido ReprogramarPedido(Long idPedido, LocalDate nuevaFecha) {
        EntidadPedido entidadPedido = jpaRepositorioPedido.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado."));

        entidadPedido.setFecha_de_pedido(nuevaFecha);
        entidadPedido.setReprogramado(true);

        EntidadPedido guardado = jpaRepositorioPedido.save(entidadPedido);
        return guardado.toDomainModel();
    }

    @Override
    public Pedido CancelarPedido(Long idPedido, long montoADevolver, int porcentajeDevuelto) {
        EntidadPedido entidadPedido = jpaRepositorioPedido.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado."));

        entidadPedido.setEstado(Estado.CANCELADO);
        entidadPedido.setEstadoPreparacion(null);
        entidadPedido.setMontoADevolver(montoADevolver);
        entidadPedido.setPorcentajeDevuelto(porcentajeDevuelto);

        EntidadPedido guardado = jpaRepositorioPedido.save(entidadPedido);
        return guardado.toDomainModel();
    }

    @Override
    public long ContarPedidosEnFecha(LocalDate fecha) {
        return jpaRepositorioPedido.contarPedidosEnFecha(fecha) + jpaRepositorioPedidoManual.sumarCantidadEnFecha(fecha);
    }

    @Override
    public ConfiguracionAgenda ObtenerConfiguracionAgenda() {
        return jpaRepositorioConfiguracionAgenda.findById(ID_CONFIGURACION_AGENDA)
                .map(EntidadConfiguracionAgenda::toDomainModel)
                .orElseGet(() -> new ConfiguracionAgenda(ID_CONFIGURACION_AGENDA, CUPO_MAXIMO_POR_DIA_POR_DEFECTO));
    }

    @Override
    public ConfiguracionAgenda ActualizarCupoMaximoPorDia(int nuevoCupo) {
        EntidadConfiguracionAgenda entidad = jpaRepositorioConfiguracionAgenda.findById(ID_CONFIGURACION_AGENDA)
                .orElse(new EntidadConfiguracionAgenda(ID_CONFIGURACION_AGENDA, CUPO_MAXIMO_POR_DIA_POR_DEFECTO));

        entidad.setCupoMaximoPorDia(nuevoCupo);

        EntidadConfiguracionAgenda guardado = jpaRepositorioConfiguracionAgenda.save(entidad);
        return guardado.toDomainModel();
    }

    @Override
    public List<DiaBloqueado> ObtenerDiasBloqueados() {
        return jpaRepositorioDiaBloqueado.findAllByOrderByFechaAsc().stream()
                .map(EntidadDiaBloqueado::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public DiaBloqueado BloquearDia(LocalDate fecha, String motivo) {
        EntidadDiaBloqueado entidad = new EntidadDiaBloqueado(fecha, motivo);
        EntidadDiaBloqueado guardado = jpaRepositorioDiaBloqueado.save(entidad);
        return guardado.toDomainModel();
    }

    @Override
    public void DesbloquearDia(LocalDate fecha) {
        jpaRepositorioDiaBloqueado.deleteByFecha(fecha);
    }

    @Override
    public boolean EstaDiaBloqueado(LocalDate fecha) {
        return jpaRepositorioDiaBloqueado.existsByFecha(fecha);
    }

    @Override
    public List<PedidoManual> ObtenerPedidosManuales() {
        return jpaRepositorioPedidoManual.findAllByOrderByFechaAsc().stream()
                .map(EntidadPedidoManual::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoManual CrearPedidoManual(LocalDate fecha, int cantidad, String motivo) {
        EntidadPedidoManual entidad = new EntidadPedidoManual(fecha, cantidad, motivo);
        EntidadPedidoManual guardado = jpaRepositorioPedidoManual.save(entidad);
        return guardado.toDomainModel();
    }

    @Override
    public void EliminarPedidoManual(Long id) {
        jpaRepositorioPedidoManual.deleteById(id);
    }

    @Override
    public boolean ExistePedidoManual(Long id) {
        return jpaRepositorioPedidoManual.existsById(id);
    }

}
