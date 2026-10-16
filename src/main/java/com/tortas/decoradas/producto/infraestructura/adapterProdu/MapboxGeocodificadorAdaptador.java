package com.tortas.decoradas.producto.infraestructura.adapterProdu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tortas.decoradas.producto.dominio.puerto.out.PuertoGeocodificadorDireccion;

public class MapboxGeocodificadorAdaptador implements PuertoGeocodificadorDireccion {

    private final RestTemplate restTemplate;
    private final String apiKey;

    public MapboxGeocodificadorAdaptador(RestTemplate restTemplate, String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Long> obtenerCodigoPostal(String calle, String altura) {
        if (apiKey == null || apiKey.isBlank()) {
            return Optional.empty();
        }
        try {
            String direccion = calle + " " + altura + ", Buenos Aires, Argentina";
            String url = UriComponentsBuilder
                    .fromHttpUrl("https://api.mapbox.com/geocoding/v5/mapbox.places/" + direccion + ".json")
                    .queryParam("access_token", apiKey)
                    .queryParam("country", "ar")
                    .queryParam("types", "address")
                    .queryParam("limit", 1)
                    .toUriString();

            Map<String, Object> respuesta = restTemplate.getForObject(url, Map.class);
            if (respuesta == null) {
                return Optional.empty();
            }

            List<Map<String, Object>> features = (List<Map<String, Object>>) respuesta.get("features");
            if (features == null || features.isEmpty()) {
                return Optional.empty();
            }

            List<Map<String, Object>> contexto = (List<Map<String, Object>>) features.get(0).get("context");
            if (contexto == null) {
                return Optional.empty();
            }

            for (Map<String, Object> item : contexto) {
                String id = String.valueOf(item.get("id"));
                if (id.startsWith("postcode")) {
                    String soloDigitos = String.valueOf(item.get("text")).replaceAll("[^0-9]", "");
                    if (!soloDigitos.isBlank()) {
                        return Optional.of(Long.parseLong(soloDigitos));
                    }
                }
            }
            return Optional.empty();
        } catch (RestClientException | NumberFormatException | ClassCastException e) {
            return Optional.empty();
        }
    }
}
