package com.tortas.decoradas.pago.infraestructura;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tortas.decoradas.pago.dominio.modelo.LineaPago;
import com.tortas.decoradas.pago.dominio.modelo.PagoRealizado;
import com.tortas.decoradas.pago.dominio.modelo.PreferenciaPago;
import com.tortas.decoradas.pago.dominio.puerto.out.PuertoPasarelaPago;

public class MercadoPagoAdaptador implements PuertoPasarelaPago {

    private static final String API = "https://api.mercadopago.com";

    private final String accessToken;
    private final String urlFrontend;
    private final String urlNotificacion;
    private final ObjectMapper mapper;
    private final int minutosParaPagar;
    private static final Logger log = LoggerFactory.getLogger(MercadoPagoAdaptador.class);
    private final HttpClient http = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10)).build();

    public MercadoPagoAdaptador(String accessToken, String urlFrontend, String urlNotificacion,
            ObjectMapper mapper, int minutosParaPagar) {
        this.minutosParaPagar = Math.max(1, minutosParaPagar);
        this.accessToken = accessToken == null ? "" : accessToken.trim();
        this.urlFrontend = urlFrontend.endsWith("/") ? urlFrontend.substring(0, urlFrontend.length() - 1)
                : urlFrontend;
        this.urlNotificacion = urlNotificacion == null ? "" : urlNotificacion.trim();
        this.mapper = mapper;
        log.info("Mercado Pago: access token {} (largo {}, prefijo {})",
                this.accessToken.isBlank() ? "VACÍO" : "cargado", this.accessToken.length(),
                this.accessToken.length() >= 4 ? this.accessToken.substring(0, 4) : "-");
    }

    @Override
    public PreferenciaPago crearPreferencia(Long idPedido, List<LineaPago> lineas) {
        List<Map<String, Object>> items = new ArrayList<>();
        for (LineaPago linea : lineas) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("title", linea.titulo());
            item.put("quantity", linea.cantidad());
            item.put("unit_price", linea.precioUnitario());
            item.put("currency_id", "ARS");
            items.add(item);
        }

        String retorno = urlFrontend + "/pago/resultado";
        log.info("Mercado Pago: URL de retorno {} (auto_return {}), webhook {}", retorno,
                urlFrontend.startsWith("https://") ? "activo" : "INACTIVO", urlNotificacion.isBlank() ? "sin configurar" : urlNotificacion);
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("items", items);
        cuerpo.put("external_reference", String.valueOf(idPedido));
        cuerpo.put("back_urls", Map.of("success", retorno, "failure", retorno, "pending", retorno));
        cuerpo.put("payment_methods", Map.of("excluded_payment_types",
                List.of(Map.of("id", "ticket"), Map.of("id", "atm"))));
        cuerpo.put("expires", true);
        cuerpo.put("expiration_date_to", OffsetDateTime.now().plusMinutes(minutosParaPagar)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx")));
        if (urlFrontend.startsWith("https://")) {
            cuerpo.put("auto_return", "approved");
        }
        if (!urlNotificacion.isBlank()) {
            cuerpo.put("notification_url", urlNotificacion);
        }

        JsonNode respuesta = enviar(HttpRequest.newBuilder(URI.create(API + "/checkout/preferences"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(escribir(cuerpo))));

        String id = respuesta.path("id").asText(null);
        String url = respuesta.path("init_point").asText(null);
        if (id == null || url == null) {
            throw new IllegalStateException("Mercado Pago no devolvió el link de pago.");
        }
        return new PreferenciaPago(id, url);
    }

    @Override
    public Optional<PagoRealizado> obtenerPago(String idPago) {
        if (idPago == null || !idPago.matches("\\d{1,20}")) {
            return Optional.empty();
        }
        try {
            return Optional.of(aPago(enviar(HttpRequest.newBuilder(URI.create(API + "/v1/payments/" + idPago)).GET())));
        } catch (PagoNoEncontrado e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PagoRealizado> buscarPagosDePedido(Long idPedido) {
        String url = API + "/v1/payments/search?sort=date_created&criteria=desc&external_reference="
                + URLEncoder.encode(String.valueOf(idPedido), StandardCharsets.UTF_8);
        JsonNode respuesta = enviar(HttpRequest.newBuilder(URI.create(url)).GET());
        List<PagoRealizado> pagos = new ArrayList<>();
        for (JsonNode nodo : respuesta.path("results")) {
            pagos.add(aPago(nodo));
        }
        return pagos;
    }

    private PagoRealizado aPago(JsonNode nodo) {
        Long idPedido = null;
        String ref = nodo.path("external_reference").asText("");
        if (ref.matches("\\d{1,18}")) {
            idPedido = Long.valueOf(ref);
        }
        return new PagoRealizado(nodo.path("id").asText(), nodo.path("status").asText(""), idPedido,
                nodo.path("transaction_amount").asDouble(0));
    }

    private JsonNode enviar(HttpRequest.Builder pedido) {
        if (accessToken.isBlank()) {
            throw new IllegalStateException("Mercado Pago no está configurado (falta el Access Token).");
        }
        try {
            HttpResponse<String> res = http.send(
                    pedido.header("Authorization", "Bearer " + accessToken).header("Accept", "application/json")
                            .header("User-Agent", "decoradas-backend/1.0").timeout(Duration.ofSeconds(20)).build(),
                    HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 404) {
                throw new PagoNoEncontrado();
            }
            if (res.statusCode() < 200 || res.statusCode() >= 300) {
                throw new IllegalStateException(
                        "Mercado Pago respondió " + res.statusCode() + ": " + res.body());
            }
            return mapper.readTree(res.body());
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo hablar con Mercado Pago.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Se interrumpió la consulta a Mercado Pago.", e);
        }
    }

    private String escribir(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static class PagoNoEncontrado extends RuntimeException {
        PagoNoEncontrado() {
            super("No encontrado", null, false, false);
        }
    }
}
