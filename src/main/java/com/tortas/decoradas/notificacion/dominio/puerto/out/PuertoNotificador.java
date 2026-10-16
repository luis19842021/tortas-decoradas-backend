package com.tortas.decoradas.notificacion.dominio.puerto.out;

import java.util.Map;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;

public interface PuertoNotificador {
    void notificar(String emailDestino, String nombreDestino, TipoNotificacion tipo, Map<String, String> datos);

    void notificarConAdjunto(String emailDestino, String nombreDestino, TipoNotificacion tipo, Map<String, String> datos,
            String nombreArchivo, byte[] contenido);
}
