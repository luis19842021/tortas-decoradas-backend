package com.tortas.decoradas.producto.infraestructura.adapterProdu;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoGeocodificadorDireccion;

public class UsigGeocodificadorAdaptador implements PuertoGeocodificadorDireccion {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UsigGeocodificadorAdaptador(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Long> obtenerCodigoPostal(String calle, String altura) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/datos_utiles")
                    .queryParam("calle", calle)
                    .queryParam("altura", altura)
                    .toUriString();

            String cuerpo = restTemplate.getForObject(url, String.class);
            if (cuerpo == null || cuerpo.isBlank()) {
                return Optional.empty();
            }

            Map<String, Object> respuesta = objectMapper.readValue(cuerpo, Map.class);
            Object codigoPostal = respuesta.get("codigo_postal");
            if (codigoPostal == null) {
                return Optional.empty();
            }

            String texto = codigoPostal.toString().trim();
            if (texto.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(Long.parseLong(texto));
        } catch (Exception e) {
            System.err.println("[UsigGeocodificadorAdaptador] Falló la consulta a USIG: "
                    + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
