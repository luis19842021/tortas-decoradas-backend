package com.tortas.decoradas.carrusel.infraestructura.controlCarrusel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.carrusel.aplicacion.servicio.ServicioCarrusel;
import com.tortas.decoradas.carrusel.dominio.modelo.ImagenCarrusel;

@RestController
@RequestMapping("/api/carrusel")
public class ControladorCarrusel {

    private final ServicioCarrusel servicioCarrusel;

    public ControladorCarrusel(ServicioCarrusel servicioCarrusel) {
        this.servicioCarrusel = servicioCarrusel;
    }

    @GetMapping
    public ResponseEntity<List<ImagenCarrusel>> obtenerCarrusel() {
        return new ResponseEntity<>(servicioCarrusel.obtenerCarrusel(), HttpStatus.OK);
    }

    @PutMapping("/{espacio}")
    public ResponseEntity<?> actualizarImagenCarrusel(@PathVariable Integer espacio,
            @RequestParam("imagen") MultipartFile imagen) {
        try {
            ImagenCarrusel actualizada = servicioCarrusel.actualizarImagenCarrusel(espacio, imagen);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException | RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo subir la imagen: " + e.getMessage());
        }
    }

    @PutMapping("/{espacio}/enlace")
    public ResponseEntity<?> actualizarEnlaceCarrusel(@PathVariable Integer espacio,
            @RequestBody Map<String, String> cuerpo) {
        try {
            ImagenCarrusel actualizada = servicioCarrusel.actualizarEnlaceCarrusel(espacio, cuerpo.get("enlace"));
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{espacio}")
    public ResponseEntity<Void> eliminarImagenCarrusel(@PathVariable Integer espacio) {
        servicioCarrusel.eliminarImagenCarrusel(espacio);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
