package com.tortas.decoradas.notificacion.infraestructura.canal;

import java.util.Map;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;

public interface CanalNotificacion {
    void enviar(String emailDestino, String nombreDestino, TipoNotificacion tipo, Map<String, String> datos);

    default void enviarConAdjunto(String emailDestino, String nombreDestino, TipoNotificacion tipo,
            Map<String, String> datos, String nombreArchivo, byte[] contenido) {
        enviar(emailDestino, nombreDestino, tipo, datos);
    }
}
