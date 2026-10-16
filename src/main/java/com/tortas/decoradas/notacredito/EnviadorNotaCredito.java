package com.tortas.decoradas.notacredito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EnviadorNotaCredito {

    private static final Logger log = LoggerFactory.getLogger(EnviadorNotaCredito.class);

    private final JavaMailSender mailSender;
    private final String remitente;

    public EnviadorNotaCredito(JavaMailSender mailSender, @Value("${app.mail.remitente}") String remitente) {
        this.mailSender = mailSender;
        this.remitente = remitente;
    }

    @Async
    public void enviar(String email, String nombre, Long idPedido, String nombreArchivo, byte[] contenido) {
        if (email == null || email.isBlank()) {
            return;
        }
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
            helper.setFrom(remitente, "Colores Tortas Decoradas");
            helper.setTo(email);
            helper.setSubject("Nota de crédito de tu pedido #" + idPedido);
            String saludo = nombre != null && !nombre.isBlank() ? ", " + nombre : "";
            helper.setText("<p>Hola" + saludo + ",</p>"
                    + "<p>Te enviamos adjunta la nota de crédito correspondiente a la cancelación de tu pedido #"
                    + idPedido + ". Refleja el monto que te devolvemos.</p>"
                    + "<p>También podés descargarla desde la sección Historial de tu cuenta.</p>"
                    + "<p>Gracias,<br>Decoradas</p>", true);
            helper.addAttachment(nombreArchivo, new ByteArrayResource(contenido), "application/pdf");
            mailSender.send(mensaje);
        } catch (Exception e) {
            log.error("No se pudo enviar la nota de crédito del pedido {}: {}", idPedido, e.getMessage());
        }
    }
}
