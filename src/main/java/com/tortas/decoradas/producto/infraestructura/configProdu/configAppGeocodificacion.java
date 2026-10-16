package com.tortas.decoradas.producto.infraestructura.configProdu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.tortas.decoradas.producto.dominio.puerto.out.PuertoGeocodificadorDireccion;
import com.tortas.decoradas.producto.infraestructura.adapterProdu.GeocodificadorConFallbackAdaptador;
import com.tortas.decoradas.producto.infraestructura.adapterProdu.MapboxGeocodificadorAdaptador;
import com.tortas.decoradas.producto.infraestructura.adapterProdu.UsigGeocodificadorAdaptador;

@Configuration
public class configAppGeocodificacion {

    @Value("${usig.base-url:https://ws.usig.buenosaires.gob.ar}")
    private String usigBaseUrl;

    @Value("${mapbox.api-key:}")
    private String mapboxApiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PuertoGeocodificadorDireccion puertoGeocodificadorDireccion(RestTemplate restTemplate) {
        PuertoGeocodificadorDireccion usig = new UsigGeocodificadorAdaptador(restTemplate, usigBaseUrl);
        PuertoGeocodificadorDireccion mapbox = new MapboxGeocodificadorAdaptador(restTemplate, mapboxApiKey);
        return new GeocodificadorConFallbackAdaptador(usig, mapbox);
    }
}
