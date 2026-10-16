package com.tortas.decoradas.pedido.aplicacion.casoDeUsoPedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pedido.dominio.modelo.ConfiguracionAgenda;
import com.tortas.decoradas.pedido.dominio.modelo.CrearPedidoRequestDto;
import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.ItemPedido;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.modelo.ReglasDeAgenda;
import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCrearPedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoCrearPedidoImpl implements CasoDeUsoCrearPedido {

    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoNotificador puertoNotificador;

    public CasoDeUsoCrearPedidoImpl(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoNotificador puertoNotificador) {
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoNotificador = puertoNotificador;
    }

    @Override
    public Pedido crearPedido(String emailUsuario, CrearPedidoRequestDto datos) {
        if (datos.getFechaDeEntrega() == null) {
            throw new IllegalArgumentException("Falta la fecha de entrega.");
        }
        if (datos.getItems() == null || datos.getItems().isEmpty()) {
            throw new IllegalArgumentException("El pedido no tiene productos.");
        }

        if (!datos.isAceptaTerminos()) {
            throw new IllegalArgumentException("Tenés que aceptar los términos y condiciones para comprar.");
        }

        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        boolean tienePendienteDePago = puertoRepositorioPedido.BuscarPorUsuario(usuario.getId()).stream()
                .anyMatch(p -> p.getEstado() == Estado.INICIADO);
        if (tienePendienteDePago) {
            throw new IllegalArgumentException(
                    "Ya tenés un pedido sin pagar. Pagalo o cancelalo antes de hacer uno nuevo.");
        }

        if (!ReglasDeAgenda.respetaAnticipacionMinima(datos.getFechaDeEntrega(), LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha de entrega tiene que tener, como mínimo, "
                            + ReglasDeAgenda.DIAS_MINIMOS_DE_ANTICIPACION + " días de anticipación.");
        }

        if (puertoRepositorioPedido.EstaDiaBloqueado(datos.getFechaDeEntrega())) {
            throw new IllegalArgumentException("Esa fecha no está disponible.");
        }

        ConfiguracionAgenda configuracionAgenda = puertoRepositorioPedido.ObtenerConfiguracionAgenda();
        long pedidosEnFecha = puertoRepositorioPedido.ContarPedidosEnFecha(datos.getFechaDeEntrega());
        if (pedidosEnFecha >= configuracionAgenda.getCupoMaximoPorDia()) {
            throw new IllegalArgumentException("Esa fecha ya no tiene cupo disponible.");
        }

        List<ItemPedido> items = datos.getItems().stream()
                .map(itemDto -> new ItemPedido(
                        null,
                        itemDto.getIdProducto(),
                        itemDto.getDescripcion(),
                        itemDto.getPrecioUnitario(),
                        itemDto.getCantidad()))
                .collect(Collectors.toList());

        Pedido pedido = new Pedido(null, datos.getFechaDeEntrega(), Estado.INICIADO, usuario.getId(), items);
        pedido.setDireccionEnvio(datos.getDireccionEnvio());
        pedido.setCodigoPostalEnvio(datos.getCodigoPostalEnvio());
        pedido.setTerminosAceptadosEn(LocalDateTime.now());
        pedido.setVersionTerminos(versionTerminos(datos.getVersionTerminos()));

        Pedido guardado = puertoRepositorioPedido.GuardarPedido(pedido);

        return guardado;
    }
    private static String versionTerminos(String version) {
        if (version == null || version.isBlank()) {
            return null;
        }
        String limpia = version.trim();
        return limpia.length() > 30 ? limpia.substring(0, 30) : limpia;
    }
}
