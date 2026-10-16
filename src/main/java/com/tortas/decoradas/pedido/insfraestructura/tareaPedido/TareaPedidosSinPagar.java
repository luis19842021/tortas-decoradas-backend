package com.tortas.decoradas.pedido.insfraestructura.tareaPedido;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.pedido.dominio.puerto.in.CasoDeUsoCancelarPedidosSinPagar;


@Component
public class TareaPedidosSinPagar {

    private static final Logger log = LoggerFactory.getLogger(TareaPedidosSinPagar.class);

    private final CasoDeUsoCancelarPedidosSinPagar casoDeUso;
    private final int minutosParaPagar;

    public TareaPedidosSinPagar(CasoDeUsoCancelarPedidosSinPagar casoDeUso,
            @Value("${pedidos.minutos-para-pagar:20}") int minutosParaPagar) {
        this.casoDeUso = casoDeUso;
        this.minutosParaPagar = minutosParaPagar;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void ejecutar() {
        int cancelados = casoDeUso.cancelarPedidosSinPagar(minutosParaPagar);
        if (cancelados > 0) {
            log.info("TareaPedidosSinPagar: {} pedido(s) sin pagar cancelado(s).", cancelados);
        }
    }
}
