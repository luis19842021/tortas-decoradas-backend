package com.tortas.decoradas.pago.infraestructura;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.pago.aplicacion.CasoDeUsoConfirmarPagoImpl;
import com.tortas.decoradas.pago.aplicacion.CasoDeUsoCrearPreferenciaPagoImpl;
import com.tortas.decoradas.pago.dominio.puerto.in.CasoDeUsoConfirmarPago;
import com.tortas.decoradas.pago.dominio.puerto.in.CasoDeUsoCrearPreferenciaPago;
import com.tortas.decoradas.pago.dominio.puerto.out.PuertoPasarelaPago;
import com.tortas.decoradas.pedido.dominio.puerto.out.PuertoRepositorioPedido;
import com.tortas.decoradas.producto.dominio.puerto.out.PuertoRepositorioProducto;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

@Configuration
public class ConfigAppPago {

    @Bean
    public PuertoPasarelaPago puertoPasarelaPago(@Value("${mercadopago.access-token:}") String accessToken,
            @Value("${app.frontend-url}") String urlFrontend,
            @Value("${mercadopago.notification-url:}") String urlNotificacion,
            @Value("${pedidos.minutos-para-pagar:20}") int minutosParaPagar) {
        return new MercadoPagoAdaptador(accessToken, urlFrontend, urlNotificacion, new ObjectMapper(),
                minutosParaPagar);
    }

    @Bean
    public CasoDeUsoCrearPreferenciaPago casoDeUsoCrearPreferenciaPago(
            PuertoRepositorioPedido puertoRepositorioPedido, PuertoRepositorioUsuario puertoRepositorioUsuario,
            PuertoRepositorioProducto puertoRepositorioProducto, PuertoPasarelaPago puertoPasarelaPago) {
        return new CasoDeUsoCrearPreferenciaPagoImpl(puertoRepositorioPedido, puertoRepositorioUsuario,
                puertoRepositorioProducto, puertoPasarelaPago);
    }

    @Bean
    public CasoDeUsoConfirmarPago casoDeUsoConfirmarPago(PuertoRepositorioPedido puertoRepositorioPedido,
            PuertoRepositorioUsuario puertoRepositorioUsuario, PuertoRepositorioProducto puertoRepositorioProducto,
            PuertoPasarelaPago puertoPasarelaPago, PuertoNotificador puertoNotificador) {
        return new CasoDeUsoConfirmarPagoImpl(puertoRepositorioPedido, puertoRepositorioUsuario,
                puertoRepositorioProducto, puertoPasarelaPago, puertoNotificador);
    }
}
