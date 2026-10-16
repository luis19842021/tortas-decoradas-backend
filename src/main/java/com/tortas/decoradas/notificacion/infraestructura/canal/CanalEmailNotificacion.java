package com.tortas.decoradas.notificacion.infraestructura.canal;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.notificacion.dominio.modelo.TipoNotificacion;

import jakarta.mail.internet.MimeMessage;

@Component
public class CanalEmailNotificacion implements CanalNotificacion {

    private static final Logger log = LoggerFactory.getLogger(CanalEmailNotificacion.class);
    private static final String NOMBRE_REMITENTE = "Decoradas";

    private final JavaMailSender mailSender;
    private final String remitente;

    public CanalEmailNotificacion(JavaMailSender mailSender, @Value("${app.mail.remitente}") String remitente) {
        this.mailSender = mailSender;
        this.remitente = remitente;
        if (mailSender instanceof JavaMailSenderImpl impl) {
            String clave = impl.getPassword();
            log.info("Configuración de mail: host={} puerto={} usuario={} largoClave={} remitente={}",
                    impl.getHost(), impl.getPort(), impl.getUsername(), clave == null ? 0 : clave.length(), remitente);
        }
    }

    @Override
    public void enviar(String emailDestino, String nombreDestino, TipoNotificacion tipo, Map<String, String> datos) {
        enviarConAdjunto(emailDestino, nombreDestino, tipo, datos, null, null);
    }

    @Override
    public void enviarConAdjunto(String emailDestino, String nombreDestino, TipoNotificacion tipo,
            Map<String, String> datos, String nombreArchivo, byte[] contenido) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            boolean conAdjunto = contenido != null && contenido.length > 0;
            MimeMessageHelper helper = new MimeMessageHelper(message, conAdjunto);
            helper.setFrom(remitente, NOMBRE_REMITENTE);
            helper.setTo(emailDestino);
            helper.setSubject(asunto(tipo));
            helper.setText(cuerpo(tipo, nombreDestino, datos), true);
            if (conAdjunto) {
                String nombre = nombreArchivo == null || nombreArchivo.isBlank() ? "factura.pdf" : nombreArchivo;
                helper.addAttachment(nombre, new ByteArrayResource(contenido), "application/pdf");
            }
            mailSender.send(message);
        } catch (Exception e) {
            log.error("No se pudo enviar el mail de notificación ({}) a {}: {}", tipo, emailDestino, e.getMessage());
        }
    }

    private String asunto(TipoNotificacion tipo) {
        switch (tipo) {
            case PAGO_CONFIRMADO:
                return "Confirmamos tu pago";
            case PAGO_CANCELADO:
                return "Tu pago fue cancelado";
            case PEDIDO_REPROGRAMADO:
                return "Reprogramamos tu entrega";
            case NUEVO_PEDIDO_ADMIN:
                return "Nuevo pedido recibido";
            case PEDIDO_REPROGRAMADO_ADMIN:
                return "Un pedido fue reprogramado";
            case PEDIDO_CANCELADO_CLIENTE:
                return "Cancelamos tu pedido";
            case PEDIDO_CANCELADO_ADMIN:
                return "Un pedido fue cancelado";
            case FACTURA_DISPONIBLE:
                return "Tu factura está disponible";
            default:
                return "Novedades de tu pedido";
        }
    }

    private String cuerpo(TipoNotificacion tipo, String nombreDestino, Map<String, String> datos) {
        String idPedido = datos.getOrDefault("idPedido", "");
        String saludo = "<p>Hola" + (nombreDestino != null && !nombreDestino.isBlank() ? ", " + nombreDestino : "") + ",</p>";

        String mensaje;
        switch (tipo) {
            case PAGO_CONFIRMADO:
                mensaje = "<p>Confirmamos el pago de tu pedido #" + idPedido + ". ¡Y lo estaremos preparando en la fecha reservada!</p>"
                        + "<p>En el transcurso del día te enviaremos la factura de tu compra por este mismo medio. "
                        + "También vas a poder descargarla desde la sección Historial de tu cuenta.</p>";
                break;
            case PAGO_CANCELADO:
                mensaje = "<p>Tu pedido #" + idPedido + " fue cancelado.</p>";
                break;
            case PEDIDO_REPROGRAMADO:
                String nuevaFecha = datos.getOrDefault("nuevaFecha", "");
                mensaje = "<p>Reprogramamos la entrega de tu pedido #" + idPedido + " para el " + nuevaFecha + ".</p>";
                break;
            case NUEVO_PEDIDO_ADMIN: {
                String cliente = datos.getOrDefault("clienteNombre", "");
                String clienteEmail = datos.getOrDefault("clienteEmail", "");
                String fechaEntrega = datos.getOrDefault("fechaEntrega", "");
                mensaje = "<p>Entró un pedido nuevo (#" + idPedido + ") de " + cliente + " (" + clienteEmail + ")"
                        + " para el " + fechaEntrega + ".</p>";
                break;
            }
            case PEDIDO_REPROGRAMADO_ADMIN: {
                String cliente = datos.getOrDefault("clienteNombre", "");
                String clienteEmail = datos.getOrDefault("clienteEmail", "");
                String nuevaFechaAdmin = datos.getOrDefault("nuevaFecha", "");
                mensaje = "<p>" + cliente + " (" + clienteEmail + ") reprogramó el pedido #" + idPedido
                        + " para el " + nuevaFechaAdmin + ".</p>";
                break;
            }
            case PEDIDO_CANCELADO_CLIENTE: {
                String porcentaje = datos.getOrDefault("porcentajeDevuelto", "0");
                String monto = datos.getOrDefault("montoADevolver", "0");
                mensaje = "<p>Cancelamos tu pedido #" + idPedido + ".</p>"
                        + "<p>Según nuestra política de cancelación, te corresponde un reembolso del "
                        + porcentaje + "% ($" + monto + "). Nos vamos a poner en contacto para gestionarlo.</p>";
                break;
            }
            case PEDIDO_CANCELADO_ADMIN: {
                String cliente = datos.getOrDefault("clienteNombre", "");
                String clienteEmail = datos.getOrDefault("clienteEmail", "");
                String porcentajeAdmin = datos.getOrDefault("porcentajeDevuelto", "0");
                String montoAdmin = datos.getOrDefault("montoADevolver", "0");
                mensaje = "<p>" + cliente + " (" + clienteEmail + ") canceló el pedido #" + idPedido + ".</p>"
                        + "<p>Le corresponde un reembolso del " + porcentajeAdmin + "% ($" + montoAdmin + ").</p>";
                break;
            }
            case FACTURA_DISPONIBLE:
                mensaje = "<p>Te enviamos adjunta la factura de tu pedido #" + idPedido + ". "
                        + "También podés descargarla cuando quieras desde la sección Historial de tu cuenta.</p>";
                break;
            default:
                mensaje = "<p>Hay novedades sobre tu pedido #" + idPedido + ".</p>";
        }

        return saludo + mensaje + "<p>Gracias,<br>ColoresTortasDecoradas</p>";
    }
}
