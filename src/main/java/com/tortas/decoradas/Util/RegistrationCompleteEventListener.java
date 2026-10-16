package com.tortas.decoradas.Util;

import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private static final Logger log = LoggerFactory.getLogger(RegistrationCompleteEventListener.class);

    @Autowired
    private JavaMailSender mailSender;
    private Usuario usuario;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Value("${app.mail.remitente}")
    private String remitente;

    @Async
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        usuario = event.getUser();
        String verificationTokentemp = event.getVerificationToken();

        String url = frontendUrl + "/registro/verificar-correo/confirmar?token=" + verificationTokentemp;

        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        log.info("Haga clic en el enlace para verificar su registro:  {}", url);

    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Verificación de Correo Electrónico";
        String senderName = "Servicio de Registro de Usuarios";
        String mailContent = "<p>Hola, " + usuario.getNombre() + ",</p>"
                + "<p>Gracias por registrarte con nosotros.</p>"
                + "<p>Por favor, haz clic en el enlace a continuación para completar tu registro:</p>"
                + "<a href=\"" + url + "\">Verifica tu correo electrónico para activar tu cuenta</a>"
                + "<p>Gracias,<br>Servicio de Registro de Usuarios</p>";

        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom(remitente, senderName);
        messageHelper.setTo(usuario.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

}
