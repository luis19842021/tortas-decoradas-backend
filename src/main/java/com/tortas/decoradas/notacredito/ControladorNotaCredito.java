package com.tortas.decoradas.notacredito;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ControladorNotaCredito {

    private final ServicioNotaCredito servicioNotaCredito;

    public ControladorNotaCredito(ServicioNotaCredito servicioNotaCredito) {
        this.servicioNotaCredito = servicioNotaCredito;
    }

    @PostMapping(value = "pedidos/{id}/nota-credito", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> subir(@PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            servicioNotaCredito.subir(id, archivo);
            return ResponseEntity.ok(Map.of("mensaje", "Nota de crédito cargada y enviada al cliente."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensaje", "No se pudo leer el archivo."));
        }
    }

    @GetMapping("pedidos/{id}/nota-credito")
    public ResponseEntity<byte[]> descargar(@PathVariable Long id, Authentication authentication) {
        try {
            EntidadNotaCredito factura = servicioNotaCredito.obtener(authentication.getName(), esAdmin(authentication), id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                            .filename(factura.getNombreArchivo()).build().toString())
                    .body(factura.getContenido());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("pedidos/{id}/nota-credito/existe")
    public ResponseEntity<Map<String, Boolean>> existe(@PathVariable Long id, Authentication authentication) {
        try {
            boolean existe = servicioNotaCredito.existe(authentication.getName(), esAdmin(authentication), id);
            return ResponseEntity.ok(Map.of("existe", existe));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("notas-credito/mias")
    public ResponseEntity<List<Long>> mias(Authentication authentication) {
        return ResponseEntity.ok(servicioNotaCredito.pedidosConNotaCreditoDe(authentication.getName()));
    }

    @GetMapping("notas-credito/existentes")
    public ResponseEntity<List<Long>> existentes(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(servicioNotaCredito.pedidosConNotaCredito(ids));
    }

    private boolean esAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(a -> "ADMIN".equals(a.getAuthority()));
    }
}
