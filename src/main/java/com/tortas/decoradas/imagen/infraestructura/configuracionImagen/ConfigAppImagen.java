package com.tortas.decoradas.imagen.infraestructura.configuracionImagen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tortas.decoradas.imagen.aplicacion.casoDeUsosDeImagen.CasoDeUsoGuardarImagenImpl;
import com.tortas.decoradas.imagen.aplicacion.servicio.ServicioImagen;
import com.tortas.decoradas.imagen.dominio.puerto.out.PuertoImagen;

@Configuration
public class ConfigAppImagen {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;
    @Value("${cloudinary.api-key}")
    private String apiKey;
    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret));
    }

    @Bean
    public ServicioImagen servicioImagen(PuertoImagen puertoImagen) {
        return new ServicioImagen(
                new CasoDeUsoGuardarImagenImpl(puertoImagen));
    }
}
