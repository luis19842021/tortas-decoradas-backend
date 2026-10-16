package com.tortas.decoradas.carrusel.infraestructura.configCarrusel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel.CasoDeUsoActualizarEnlaceCarruselImpl;
import com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel.CasoDeUsoActualizarImagenCarruselImpl;
import com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel.CasoDeUsoEliminarImagenCarruselImpl;
import com.tortas.decoradas.carrusel.aplicacion.casoDeUsoCarrusel.CasoDeUsoObtenerCarruselImpl;
import com.tortas.decoradas.carrusel.aplicacion.servicio.ServicioCarrusel;
import com.tortas.decoradas.carrusel.dominio.puerto.out.PuertoRepositorioCarrusel;
import com.tortas.decoradas.imagen.dominio.puerto.out.PuertoImagen;

@Configuration
public class ConfigAppCarrusel {

    @Bean
    public ServicioCarrusel servicioCarrusel(PuertoRepositorioCarrusel puertoRepositorioCarrusel,
            PuertoImagen puertoImagen) {
        return new ServicioCarrusel(
                new CasoDeUsoObtenerCarruselImpl(puertoRepositorioCarrusel),
                new CasoDeUsoActualizarImagenCarruselImpl(puertoRepositorioCarrusel, puertoImagen),
                new CasoDeUsoEliminarImagenCarruselImpl(puertoRepositorioCarrusel),
                new CasoDeUsoActualizarEnlaceCarruselImpl(puertoRepositorioCarrusel));
    }
}
