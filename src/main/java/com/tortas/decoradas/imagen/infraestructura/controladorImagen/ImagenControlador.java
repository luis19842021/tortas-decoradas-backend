package com.tortas.decoradas.imagen.infraestructura.controladorImagen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tortas.decoradas.imagen.aplicacion.servicio.ServicioImagen;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/api")
public class ImagenControlador {

private final ServicioImagen servicioImagen;

    public ImagenControlador(ServicioImagen servicioImagen) {
        this.servicioImagen = servicioImagen;
    }

    @PatchMapping("/upload")
     public ResponseEntity<String> probando(@RequestParam("file") MultipartFile file) {

        try {
            String url = servicioImagen.guardarImagen(file);
            return ResponseEntity.ok(url);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en /api/upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo subir la imagen: " + e.getMessage());
        }
   }

}
