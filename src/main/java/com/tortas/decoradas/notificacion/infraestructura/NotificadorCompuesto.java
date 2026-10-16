package com.tortas.decoradas.notificacion.infraestructura;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;
import com.tortas.decoradas.notificacion.dominio.puerto.out.PuertoNotificador;
import com.tortas.decoradas.notificacion.infraestructura.canal.CanalNotificacion;

@Component
public class NotificadorCompuesto implements PuertoNotificador {

    private static final Logger log = LoggerFactory.getLogger(NotificadorCompuesto.class);

    private final List<CanalNotificacion> canales;

    public NotificadorCompuesto(List<CanalNotificacion> canales) {
        this.canales = canales;
    }

    @Async
    @Override
    public void notificar(String emailDestino, String nombreDestino, TipoNotificacion tipo, Map<String, String> datos) {
        for (CanalNotificacion canal : canales) {
            try {
                canal.enviar(emailDestino, nombreDestino, tipo, datos);
            } catch (Exception e) {
                log.error("Falló el canal {} notificando {}: {}", canal.getClass().getSimpleName(), tipo, e.getMessage());
            }
        }
    }

    @Async
    @Override
    public void notificarConAdjunto(String emailDestino, String nombreDestino, TipoNotificacion tipo,
            Map<String, String> datos, String nombreArchivo, byte[] contenido) {
        for (CanalNotificacion canal : canales) {
            try {
                canal.enviarConAdjunto(emailDestino, nombreDestino, tipo, datos, nombreArchivo, contenido);
            } catch (Exception e) {
                log.error("Falló el canal {} notificando {}: {}", canal.getClass().getSimpleName(), tipo, e.getMessage());
            }
        }
    }
}
