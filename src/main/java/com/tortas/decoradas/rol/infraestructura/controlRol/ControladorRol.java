package com.tortas.decoradas.rol.infraestructura.controlRol;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortas.decoradas.rol.aplicacion.servicio.ServicioRol;

@RestController
@RequestMapping("/api")
public class ControladorRol {

    private final ServicioRol servicioRol;

    public ControladorRol(ServicioRol servicioRol) {
        this.servicioRol = servicioRol;
    }

}
