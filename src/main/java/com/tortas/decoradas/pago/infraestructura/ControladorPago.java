package com.tortas.decoradas.pago.infraestructura;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tortas.decoradas.pago.dominio.modelo.PreferenciaPago;
import com.tortas.decoradas.pago.dominio.modelo.ResultadoConfirmacionPago;
import com.tortas.decoradas.pago.dominio.puerto.in.CasoDeUsoConfirmarPago;
import com.tortas.decoradas.pago.dominio.puerto.in.CasoDeUsoCrearPreferenciaPago;

@RestController
@RequestMapping("/api/pagos")
public class ControladorPago {

    private static final Logger log = LoggerFactory.getLogger(ControladorPago.class);

    private final CasoDeUsoCrearPreferenciaPago crearPreferenciaPago;
    private final CasoDeUsoConfirmarPago confirmarPago;

    public ControladorPago(CasoDeUsoCrearPreferenciaPago crearPreferenciaPago,
            CasoDeUsoConfirmarPago confirmarPago) {
        this.crearPreferenciaPago = crearPreferenciaPago;
        this.confirmarPago = confirmarPago;
    }

    @PostMapping("preferencia")
    public ResponseEntity<?> crearPreferencia(@RequestBody Map<String, Long> body, Authentication authentication) {
        try {
            PreferenciaPago preferencia = crearPreferenciaPago.crearPreferenciaPago(authentication.getName(),
                    body.get("idPedido"));
            return ResponseEntity.ok(Map.of("idPreferencia", preferencia.idPreferencia(), "urlPago",
                    preferencia.urlPago()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        } catch (IllegalStateException e) {
            log.error("Error al crear el cobro en Mercado Pago", e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(Map.of("mensaje", "No se pudo iniciar el pago con Mercado Pago."));
        }
    }

    @PostMapping("confirmar")
    public ResponseEntity<?> confirmar(@RequestBody Map<String, Long> body, Authentication authentication) {
        try {
            ResultadoConfirmacionPago resultado = confirmarPago.confirmarDesdeRetorno(authentication.getName(),
                    body.get("idPedido"));
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        } catch (IllegalStateException e) {
            log.error("Error al consultar el pago en Mercado Pago", e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(Map.of("mensaje", "No se pudo consultar el pago en Mercado Pago."));
        }
    }

    @PostMapping("webhook")
    public ResponseEntity<Void> webhook(@RequestParam Map<String, String> params,
            @RequestBody(required = false) Map<String, Object> body) {
        String tipo = params.getOrDefault("type", params.get("topic"));
        String idPago = params.getOrDefault("data.id", params.get("id"));
        if (body != null) {
            if (tipo == null && body.get("type") != null) {
                tipo = String.valueOf(body.get("type"));
            }
            if (idPago == null && body.get("data") instanceof Map<?, ?> data && data.get("id") != null) {
                idPago = String.valueOf(data.get("id"));
            }
        }
        if (!"payment".equals(tipo) || idPago == null) {
            return ResponseEntity.ok().build();
        }
        try {
            confirmarPago.confirmarDesdeWebhook(idPago);
        } catch (IllegalStateException e) {
            log.error("Error procesando el webhook de Mercado Pago", e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
        return ResponseEntity.ok().build();
    }
}
