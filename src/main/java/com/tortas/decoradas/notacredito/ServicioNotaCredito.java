package com.tortas.decoradas.notacredito;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.pedido.dominio.modelo.Estado;
import com.tortas.decoradas.pedido.dominio.modelo.Pedido;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

@Service
public class ServicioNotaCredito {

    private static final long TAMANO_MAXIMO = 5L * 1024 * 1024;

    private final JpaRepositorioNotaCredito repositorio;
    private final PuertoRepositorioPedido puertoRepositorioPedido;
    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final EnviadorNotaCredito enviador;

    public ServicioNotaCredito(JpaRepositorioNotaCredito repositorio, PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, EnviadorNotaCredito enviador) {
        this.repositorio = repositorio;
        this.puertoRepositorioPedido = puertoRepositorioPedido;
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.enviador = enviador;
    }

    @Transactional
    public void subir(Long idPedido, MultipartFile archivo) throws IOException {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("Elegí el PDF de la nota de crédito.");
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
        if (pedido.getEstado() != Estado.CANCELADO) {
            throw new IllegalArgumentException("Solo se puede cargar una nota de crédito en pedidos cancelados.");
        }
        Usuario cliente = puertoRepositorioUsuario.buscarPorId(pedido.getId_usuario())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));

        EntidadNotaCredito nota = repositorio.findByPedidoId(idPedido).orElseGet(EntidadNotaCredito::new);
        String nombre = "NotaCredito-pedido-" + idPedido + ".pdf";
        nota.setPedidoId(idPedido);
        nota.setNombreArchivo(nombre);
        nota.setContenido(contenido);
        nota.setSubidaEn(LocalDateTime.now());
        repositorio.save(nota);

        enviador.enviar(cliente.getEmail(), cliente.getNombre(), idPedido, nombre, contenido);
    }

    @Transactional(readOnly = true)
    public EntidadNotaCredito obtener(String emailSolicitante, boolean esAdmin, Long idPedido) {
        EntidadNotaCredito nota = repositorio.findByPedidoId(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("El pedido todavía no tiene nota de crédito."));
        if (!esAdmin) {
            verificarPropietario(emailSolicitante, idPedido);
        }
        return nota;
    }

    @Transactional(readOnly = true)
    public boolean existe(String emailSolicitante, boolean esAdmin, Long idPedido) {
        if (!esAdmin) {
            verificarPropietario(emailSolicitante, idPedido);
        }
        return repositorio.existsByPedidoId(idPedido);
    }

    @Transactional(readOnly = true)
    public List<Long> pedidosConNotaCreditoDe(String emailSolicitante) {
        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(emailSolicitante)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        List<Long> ids = puertoRepositorioPedido.BuscarPorUsuario(usuario.getId()).stream()
                .map(Pedido::getIdpedido).toList();
        if (ids.isEmpty()) {
            return List.of();
        }
        return repositorio.pedidosConNotaCredito(ids);
    }

    @Transactional(readOnly = true)
    public List<Long> pedidosConNotaCredito(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return repositorio.pedidosConNotaCredito(ids);
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
