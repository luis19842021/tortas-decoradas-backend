package com.tortas.decoradas.pedido.insfraestructura.tareaPedido;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.pedido.aplicacion.servicio.ServicioPedido;


@Component
public class TareaCoccionAutomatica {

    private static final Logger log = LoggerFactory.getLogger(TareaCoccionAutomatica.class);

    private final ServicioPedido servicioPedido;

    public TareaCoccionAutomatica(ServicioPedido servicioPedido) {
        this.servicioPedido = servicioPedido;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void ejecutar() {
        int actualizados = servicioPedido.iniciarCoccionParaPedidosProximos();
        if (actualizados > 0) {
            log.info("TareaCoccionAutomatica: {} pedido(s) pasaron a COCINANDO.", actualizados);
        }
    }
}
