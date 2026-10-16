package com.tortas.decoradas.Auth.infraestructura.adapterAuth;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.Auth.dominio.modelo.EmailNoVerificadoException;
import com.tortas.decoradas.Auth.dominio.modelo.ResendLimitExceededException;
import com.tortas.decoradas.Auth.dominio.modelo.Tokentemp;
import com.tortas.decoradas.Auth.dominio.puerto.out.PuertoRepositorioAuth;
import com.tortas.decoradas.Auth.infraestructura.entiAuth.EntidadPasswordResetToken;
import com.tortas.decoradas.Auth.infraestructura.entiAuth.EntidadTokentemp;
import com.tortas.decoradas.Auth.infraestructura.repoAuth.JpaRepositorioAuth;
import com.tortas.decoradas.Auth.infraestructura.repoAuth.JpaRepositorioPasswordResetToken;
import com.tortas.decoradas.Auth.infraestructura.repoAuth.JpaRepositorioToken;
import com.tortas.decoradas.Util.JwtUtils;
import com.tortas.decoradas.Util.RegistrationCompleteEvent;
import com.tortas.decoradas.rol.infraestructura.entiRol.EntidadRol;
import com.tortas.decoradas.rol.infraestructura.repoRol.JpaRepositorioRol;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class JpaRepositorioAuthAdaptador implements PuertoRepositorioAuth {

    private static final Logger log = LoggerFactory.getLogger(JpaRepositorioAuthAdaptador.class);

    private static final int MAX_RESEND_ATTEMPTS = 3;

    private static final int RESEND_WINDOW_HOURS = 5;

    private final JpaRepositorioToken jpaRepositorioToken;
    private final JpaRepositorioPasswordResetToken jpaRepositorioPasswordResetToken;
    private final JpaRepositorioAuth jpaRepositorioAuth;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JpaRepositorioRol jpaRepositorioRol;
    private final JavaMailSender mailSender;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Value("${admin.bootstrap.email:}")
    private String emailAdminBootstrap;

    @Value("${app.mail.remitente}")
    private String remitente;

    public JpaRepositorioAuthAdaptador(JpaRepositorioToken jpaRepositorioToken,
            JpaRepositorioPasswordResetToken jpaRepositorioPasswordResetToken,
            JpaRepositorioAuth jpaRepositorioAuth,
            PasswordEncoder passwordEncoder,
            ApplicationEventPublisher eventPublisher, AuthenticationManager authenticationManager,
            JpaRepositorioRol jpaRepositorioRol, JavaMailSender mailSender) {
        this.jpaRepositorioToken = jpaRepositorioToken;
        this.jpaRepositorioPasswordResetToken = jpaRepositorioPasswordResetToken;
        this.jpaRepositorioAuth = jpaRepositorioAuth;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
        this.authenticationManager = authenticationManager;
        this.jpaRepositorioRol = jpaRepositorioRol;
        this.mailSender = mailSender;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public void saveUserVerificationToken(Usuario theUser, String verificationToken) {

        Optional<EntidadUsuario> usuarioExistente = jpaRepositorioAuth.findByEmail(theUser.getEmail());

        if (usuarioExistente.isPresent()) {
            EntidadUsuario entidadUsuario = usuarioExistente.get();

            EntidadTokentemp tokenExistente = jpaRepositorioToken.findByUsuario(entidadUsuario);

            if (tokenExistente != null) {
                tokenExistente.actualizarToken(verificationToken);
                jpaRepositorioToken.save(tokenExistente);
            } else {
                EntidadTokentemp entidadToken = new EntidadTokentemp(verificationToken, entidadUsuario);
                jpaRepositorioToken.save(entidadToken);
            }
        } else {
            EntidadUsuario entidadUsuarioNuevo = EntidadUsuario.fromDomainModel(theUser);
            EntidadTokentemp entidadToken = new EntidadTokentemp(verificationToken, entidadUsuarioNuevo);
            jpaRepositorioToken.save(entidadToken);
        }

    }

    @Override
    public String login(String email, String password) {

        Optional<EntidadUsuario> optionalUser = jpaRepositorioAuth.findByEmail(email);
        System.out.println("Usuario encontrado: " + optionalUser.isPresent());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        EntidadUsuario user = optionalUser.get();
        System.out.println("Usuario encontrado: " + user.getNombre() + ", Habilitado: " + user.isEnabled());

        var authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), password);
        var authenticate = authenticationManager.authenticate(authToken);

        if (!user.isEnabled()) {
            throw new RuntimeException("La cuenta no ha sido verificada");
        }

        boolean esAdmin = user.getRoles().stream()
                .anyMatch(rol -> "ADMIN".equalsIgnoreCase(rol.getNombre()));

        var jwtToken = JwtUtils.generateToken(
                ((UserDetails) (authenticate.getPrincipal())).getUsername(), user.getNombre(), esAdmin);
        System.out.println("Token JWT generado: " + jwtToken);
        return jwtToken;
    }

    @Override
    public String signUp(String nombre, String apellido, String contraseña, String email, String telefono) {

        Optional<EntidadUsuario> usuarioExistente = jpaRepositorioAuth.findByEmail(email);
        if (usuarioExistente.isPresent()) {
            if (usuarioExistente.get().isEnabled()) {
                throw new RuntimeException("el email ya esta registrado");
            } else {
                throw new EmailNoVerificadoException(
                        "el email ya esta registrado pero la cuenta no fue validada");
            }
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setContraseña(passwordEncoder.encode(contraseña));
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        String nombreRol = correspondeAdminPrincipal(email) ? "ADMIN" : "USER";
        EntidadRol rol = jpaRepositorioRol.findByNombre(nombreRol).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRoles(Collections.singletonList(rol.toDomainModel()));

        String verificationToken = UUID.randomUUID().toString();
        saveUserVerificationToken(usuario, verificationToken);

        eventPublisher.publishEvent(new RegistrationCompleteEvent(usuario, "...", verificationToken));

        return "Usuario registrado exitosamente";
    }

    private boolean correspondeAdminPrincipal(String email) {
        if (emailAdminBootstrap == null || emailAdminBootstrap.isBlank() || email == null
                || !emailAdminBootstrap.trim().equalsIgnoreCase(email.trim())) {
            return false;
        }
        boolean yaHayAdmin = jpaRepositorioAuth.findAll().stream()
                .anyMatch(u -> u.getRoles().stream().anyMatch(r -> "ADMIN".equalsIgnoreCase(r.getNombre())));
        return !yaHayAdmin;
    }

    @Override
    public String validateToken(String theToken) {
        EntidadTokentemp token = jpaRepositorioToken.findByToken(theToken);
        if (token == null) {
            System.out.println("Token no encontrado en la base de datos.");
            return "Token de verificación no válido";
        }
        EntidadUsuario user = token.getUsuario();
        System.out.println("Estado actual del usuario: " + user.isEnabled());

        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            jpaRepositorioToken.delete(token);
            System.out.println("Token expirado y eliminado.");
            return "expired";
        }

        user.setEnabled(true);
        try {
            jpaRepositorioAuth.save(user);
            System.out.println("Usuario actualizado. Nuevo estado: " + user.isEnabled());
            jpaRepositorioToken.delete(token);
            return "valido";
        } catch (Exception e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
            e.printStackTrace();
            return "Error al actualizar usuario";
        }

    }

    @Override
    public String verifyToken(String token) {
        throw new UnsupportedOperationException("Unimplemented method 'verifyToken'");
    }

    @Override
    public Tokentemp buscarToken(String token) {
        return jpaRepositorioToken.findByToken(token).toDomainModel();
    }

    @Override
    public void resendVerificationToken(String email) {
        Optional<EntidadUsuario> optionalUser = jpaRepositorioAuth.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("No existe una cuenta registrada con ese correo electrónico");
        }

        EntidadUsuario user = optionalUser.get();

        if (user.isEnabled()) {
            throw new RuntimeException("La cuenta ya fue verificada. Por favor, inicia sesión");
        }

        EntidadTokentemp tokenExistente = jpaRepositorioToken.findByUsuario(user);

        if (tokenExistente != null) {
            Date ahora = new Date();
            long ventanaMillis = RESEND_WINDOW_HOURS * 60L * 60_000L;

            Date ventanaInicio = tokenExistente.getVentanaInicioReenvio();
            boolean ventanaVigente = ventanaInicio != null
                    && (ahora.getTime() - ventanaInicio.getTime()) < ventanaMillis;

            if (ventanaVigente) {
                if (tokenExistente.getIntentosReenvio() >= MAX_RESEND_ATTEMPTS) {
                    long msRestantes = ventanaMillis - (ahora.getTime() - ventanaInicio.getTime());
                    long horasRestantes = Math.max(1, (long) Math.ceil(msRestantes / 3_600_000.0));

                    throw new ResendLimitExceededException(
                            "Alcanzaste el límite de " + MAX_RESEND_ATTEMPTS
                                    + " reenvíos. Intenta nuevamente en " + horasRestantes + " hora(s)");
                }

                tokenExistente.setIntentosReenvio(tokenExistente.getIntentosReenvio() + 1);
            } else {
                tokenExistente.setVentanaInicioReenvio(ahora);
                tokenExistente.setIntentosReenvio(1);
            }

            jpaRepositorioToken.save(tokenExistente);
        }

        String nuevoToken = UUID.randomUUID().toString();
        saveUserVerificationToken(user.toDomainModel(), nuevoToken);

        eventPublisher.publishEvent(new RegistrationCompleteEvent(user.toDomainModel(), "...", nuevoToken));
    }

    @Override
    public void solicitarRecuperacionContrasenia(String email) {
        Optional<EntidadUsuario> usuarioOpt = jpaRepositorioAuth.findByEmail(email);
        if (usuarioOpt.isEmpty() || !usuarioOpt.get().isEnabled()) {
            log.info("Recuperación de contraseña pedida para un email sin cuenta verificada: {}", email);
            return;
        }

        EntidadUsuario usuario = usuarioOpt.get();
        String nuevoToken = UUID.randomUUID().toString();

        EntidadPasswordResetToken tokenExistente = jpaRepositorioPasswordResetToken.findByUsuario(usuario);
        if (tokenExistente != null) {
            tokenExistente.actualizarToken(nuevoToken);
            jpaRepositorioPasswordResetToken.save(tokenExistente);
        } else {
            jpaRepositorioPasswordResetToken.save(new EntidadPasswordResetToken(nuevoToken, usuario));
        }

        String url = frontendUrl + "/registro/recuperarCuenta/confirmar?token=" + nuevoToken;

        try {
            enviarMailDeRecuperacion(usuario.getEmail(), usuario.getNombre(), url);
        } catch (Exception e) {
            log.error("No se pudo enviar el mail de recuperación de contraseña a {}: {}", email, e.getMessage());
        }
    }

    private void enviarMailDeRecuperacion(String emailDestino, String nombreDestino, String url)
            throws MessagingException, UnsupportedEncodingException {
        String asunto = "Recuperá tu contraseña";
        String cuerpo = "<p>Hola, " + nombreDestino + ",</p>"
                + "<p>Recibimos un pedido para restablecer la contraseña de tu cuenta.</p>"
                + "<p>Si fuiste vos, hacé clic en el siguiente enlace para elegir una contraseña nueva "
                + "(válido por 30 minutos):</p>"
                + "<a href=\"" + url + "\">Restablecer mi contraseña</a>"
                + "<p>Si no fuiste vos, podés ignorar este correo: tu contraseña actual sigue funcionando.</p>"
                + "<p>Gracias,<br>Decoradas</p>";

        MimeMessage message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        helper.setFrom(remitente, "Decoradas");
        helper.setTo(emailDestino);
        helper.setSubject(asunto);
        helper.setText(cuerpo, true);
        mailSender.send(message);
    }

    @Override
    public String validarTokenRecuperacion(String token) {
        EntidadPasswordResetToken entidadToken = jpaRepositorioPasswordResetToken.findByToken(token);
        if (entidadToken == null) {
            return "invalido";
        }

        if (entidadToken.getExpirationTime().getTime() - Calendar.getInstance().getTime().getTime() <= 0) {
            jpaRepositorioPasswordResetToken.delete(entidadToken);
            return "expired";
        }

        return "valido";
    }

    @Override
    public void restablecerContrasenia(String token, String nuevaContrasenia) {
        if (nuevaContrasenia == null || nuevaContrasenia.length() < 6) {
            throw new IllegalArgumentException("La contraseña nueva tiene que tener al menos 6 caracteres.");
        }

        EntidadPasswordResetToken entidadToken = jpaRepositorioPasswordResetToken.findByToken(token);
        if (entidadToken == null) {
            throw new IllegalArgumentException("El link de recuperación no es válido.");
        }

        if (entidadToken.getExpirationTime().getTime() - Calendar.getInstance().getTime().getTime() <= 0) {
            jpaRepositorioPasswordResetToken.delete(entidadToken);
            throw new IllegalArgumentException("El link de recuperación venció. Pedí uno nuevo.");
        }

        EntidadUsuario usuario = entidadToken.getUsuario();
        usuario.setContraseña(passwordEncoder.encode(nuevaContrasenia));
        jpaRepositorioAuth.save(usuario);

        jpaRepositorioPasswordResetToken.delete(entidadToken);
    }

}
