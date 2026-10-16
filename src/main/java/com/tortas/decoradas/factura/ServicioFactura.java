package com.tortas.decoradas.factura;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;
import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

@Service
public class ServicioFactura {

    private static final long TAMANO_MAXIMO = 5L * 1024 * 1024;

    private final JpaRepositorioFactura repositorio;
    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PuertoNotificador puertoNotificador;

    public ServicioFactura(JpaRepositorioFactura repositorio, PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoNotificador puertoNotificador) {
        this.repositorio = repositorio;
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.puertoNotificador = puertoNotificador;
    }

    @Transactional
    public void subir(Long idPedido, MultipartFile archivo) throws IOException {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("Elegí el PDF de la factura.");
        }
        if (archivo.getSize() > TAMANO_MAXIMO) {
            throw new IllegalArgumentException("El archivo supera los 5 MB.");
        }
        byte[] contenido = archivo.getBytes();
        if (!esPdf(contenido)) {
            throw new IllegalArgumentException("El archivo debe ser un PDF.");
        }
        Pedido pedido = puertoRepositorioPedido.BuscarPorId(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }
        Usuario cliente = puertoRepositorioUsuario.buscarPorId(pedido.getId_usuario())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));

        EntidadFactura factura = repositorio.findByPedidoId(idPedido).orElseGet(EntidadFactura::new);
        String nombre = "Factura-pedido-" + idPedido + ".pdf";
        factura.setPedidoId(idPedido);
        factura.setNombreArchivo(nombre);
        factura.setContenido(contenido);
        factura.setSubidaEn(LocalDateTime.now());
        repositorio.save(factura);

        puertoNotificador.notificarConAdjunto(cliente.getEmail(), cliente.getNombre(),
                TipoNotificacion.FACTURA_DISPONIBLE, Map.of("idPedido", String.valueOf(idPedido)), nombre,
                contenido);
    }

    @Transactional(readOnly = true)
    public EntidadFactura obtener(String emailSolicitante, boolean esAdmin, Long idPedido) {
        EntidadFactura factura = repositorio.findByPedidoId(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("El pedido todavía no tiene factura."));
        if (!esAdmin) {
            verificarPropietario(emailSolicitante, idPedido);
        }
        return factura;
    }

    @Transactional(readOnly = true)
    public boolean existe(String emailSolicitante, boolean esAdmin, Long idPedido) {
        if (!esAdmin) {
            verificarPropietario(emailSolicitante, idPedido);
        }
        return repositorio.existsByPedidoId(idPedido);
    }

    @Transactional(readOnly = true)
    public List<Long> pedidosConFacturaDe(String emailSolicitante) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailSolicitante)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        List<Long> ids = puertoRepositorioPedido.BuscarPorUsuario(usuario.getId()).stream()
                .map(Pedido::getIdpedido).toList();
        if (ids.isEmpty()) {
            return List.of();
        }
        return repositorio.pedidosConFactura(ids);
    }

    @Transactional(readOnly = true)
    public List<Long> pedidosConFactura(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return repositorio.pedidosConFactura(ids);
    }

    private void verificarPropietario(String emailSolicitante, Long idPedido) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailSolicitante)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        Pedido pedido = puertoRepositorioPedido.BuscarPorId(idPedido);
        if (pedido == null || !usuario.getId().equals(pedido.getId_usuario())) {
            throw new SecurityException("El pedido no pertenece al usuario.");
        }
    }

    private boolean esPdf(byte[] contenido) {
        return contenido.length > 4 && contenido[0] == '%' && contenido[1] == 'P' && contenido[2] == 'D'
                && contenido[3] == 'F';
    }
}
