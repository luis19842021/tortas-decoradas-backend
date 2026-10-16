package com.tortas.decoradas.Auth.infraestructura.controlAuth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortas.decoradas.Auth.dominio.modelo.AuthRequestDto;
import com.tortas.decoradas.Auth.dominio.modelo.AuthResponseDto;
import com.tortas.decoradas.Auth.dominio.modelo.AuthStatus;
import com.tortas.decoradas.Auth.dominio.modelo.EmailNoVerificadoException;
import com.tortas.decoradas.Auth.dominio.modelo.RecuperarContraseniaRequestDto;
import com.tortas.decoradas.Auth.dominio.modelo.ResendLimitExceededException;
import com.tortas.decoradas.Auth.dominio.modelo.RestablecerContraseniaRequestDto;
import com.tortas.decoradas.Auth.dominio.modelo.Tokentemp;
import com.tortas.decoradas.Auth.dominio.modelo.VerificarEmailResponseDto;

import com.tortas.decoradas.Auth.aplicacion.servicio.ServicioAuth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/auth")
public class AuthControler {

    private final ServicioAuth authService;

    public AuthControler(ServicioAuth authService) {
        this.authService = authService;
    }
     @PostMapping("/login")
     public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto){

        try {
            var jwtToken = authService.login(authRequestDto.email(), authRequestDto.contraseña());

            var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus. LOGIN_SUCCESS, "Inicio de sesión exitoso");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authResponseDto);

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            AuthStatus status = AuthStatus.LOGIN_FAILED;

            if (e.getMessage().contains("Bad credentials")
                    || e.getMessage().contains("Usuario no encontrado")) {
                errorMessage = "Email o contraseña inválidos";
            } else if (e.getMessage().contains("La cuenta no ha sido verificada")) {
                errorMessage = "El correo electrónico no ha sido verificado. Por favor, verifica tu correo.";
            } else if (e.getMessage().contains("User is disabled")) {
                errorMessage = "Tu cuenta fue suspendida. Contactá al administrador para más información.";
            } else if (e.getMessage().contains("Unable to acquire JDBC Connection")) {
                errorMessage = "El servidor de base de datos no está disponible. Por favor, inténtalo de nuevo más tarde.";
            }

            var authResponseDto = new AuthResponseDto(null, status, errorMessage);

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(authResponseDto);
        }
     }

    @PostMapping("/registrar")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto authRequestDto) {
        try {
            var jwtToken = authService.signUp(authRequestDto.nombre(), authRequestDto.apellido(),
                    authRequestDto.contraseña(), authRequestDto.email(), authRequestDto.telefono());

            var authResponseDto = new AuthResponseDto(jwtToken,
                    AuthStatus.USER_CREATED_SUCCESSFULLY,
                    "Usuario creado con exito. Por favor, revise, tu correo electronico y verifica tu cuenta para completar el registro");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authResponseDto);

        } catch (EmailNoVerificadoException e) {
            var authResponseDto = new AuthResponseDto(null,
                    AuthStatus.EMAIL_REGISTERED_NOT_VERIFIED,
                    "Ya existe una cuenta registrada con este email pendiente de confirmación");

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(authResponseDto);

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            AuthStatus status = AuthStatus.USER_NOT_CREATED;

            if (e.getMessage().contains("Unable to acquire JDBC Connection [HikariPool-1 - Connection is not available, request timed out after 2000ms (total=0, active=0, idle=0, waiting=0)] [n/a]")) {
                errorMessage = "El servidor de base de datos no está disponible. Por favor, inténtalo de nuevo más tarde.";

                var authResponseDto = new AuthResponseDto(null, status, errorMessage);
                return ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(authResponseDto);
            } else if (e.getMessage().contains("el email ya esta registrado")) {
                errorMessage = "El correo electrónico ya está registrado";
            }

            var authResponseDto = new AuthResponseDto(null, status, errorMessage);

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(authResponseDto);
        }
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<VerificarEmailResponseDto> verifyEmail(@RequestParam("token") String token) {
        System.out.println("Recibida solicitud de verificación para token: " + token);

        try {
            Tokentemp entidadToken = authService.buscarToken(token);

            if (entidadToken == null || entidadToken.getToken() == null) {
                System.out.println("Token no encontrado");
                var body = new VerificarEmailResponseDto(AuthStatus.EMAIL_VERIFICATION_INVALID,
                        "Token de verificación no válido. Por favor, verifica tu correo electrónico para obtener un token válido.",
                        null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            String email = entidadToken.getUsuario() != null ? entidadToken.getUsuario().getEmail() : null;

            String validationResult = authService.validateToken(entidadToken.getToken());
            System.out.println("Resultado de la validación: " + validationResult);

            switch (validationResult) {
                case "valido": {
                    var body = new VerificarEmailResponseDto(AuthStatus.EMAIL_VERIFIED,
                            "Correo electrónico verificado con éxito. Ahora puedes iniciar sesión.", email);
                    return ResponseEntity.status(HttpStatus.OK).body(body);
                }
                case "expired": {
                    var body = new VerificarEmailResponseDto(AuthStatus.EMAIL_VERIFICATION_EXPIRED,
                            "El enlace de verificación venció. Te podemos reenviar uno nuevo.", email);
                    return ResponseEntity.status(HttpStatus.GONE).body(body);
                }
                default: {
                    var body = new VerificarEmailResponseDto(AuthStatus.EMAIL_VERIFICATION_INVALID,
                            "Token de verificación no válido. Por favor, verifica tu correo electrónico para obtener un token válido.",
                            null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
                }
            }

        } catch (Exception e) {
            System.out.println("Error durante la verificación: " + e.getMessage());
            e.printStackTrace();
            var body = new VerificarEmailResponseDto(AuthStatus.EMAIL_VERIFICATION_INVALID,
                    "Ocurrió un error durante la verificación del correo electrónico. Por favor, inténtalo de nuevo más tarde.",
                    null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/resendVerificationToken")
    public ResponseEntity<AuthResponseDto> resendVerificationToken(@RequestParam("email") String email) {
        try {
            authService.reenviarTokenVerificacion(email);

            var authResponseDto = new AuthResponseDto(null,
                    AuthStatus.VERIFICATION_EMAIL_RESENT,
                    "Te reenviamos el correo de verificación. Por favor, revisa tu bandeja de entrada");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authResponseDto);

        } catch (ResendLimitExceededException e) {
            var authResponseDto = new AuthResponseDto(null, AuthStatus.VERIFICATION_EMAIL_NOT_RESENT, e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(authResponseDto);

        } catch (Exception e) {
            String errorMessage = e.getMessage();

            if (errorMessage != null && errorMessage.contains("No existe una cuenta registrada")) {
                errorMessage = "No existe una cuenta registrada con ese correo electrónico";
            } else if (errorMessage != null && errorMessage.contains("La cuenta ya fue verificada")) {
                errorMessage = "La cuenta ya fue verificada. Por favor, inicia sesión";
            }

            var authResponseDto = new AuthResponseDto(null, AuthStatus.VERIFICATION_EMAIL_NOT_RESENT, errorMessage);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(authResponseDto);
        }
    }

    @GetMapping("/verificarAdmin")
    public ResponseEntity<Void> verificarAdmin() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recuperarContrasenia")
    public ResponseEntity<AuthResponseDto> recuperarContrasenia(@RequestBody RecuperarContraseniaRequestDto datos) {
        authService.solicitarRecuperacion(datos.email());

        var body = new AuthResponseDto(null, AuthStatus.RECUPERACION_SOLICITADA,
                "Si el email está registrado y verificado, te enviamos un correo con instrucciones para restablecer tu contraseña.");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/validarTokenRecuperacion")
    public ResponseEntity<AuthResponseDto> validarTokenRecuperacion(@RequestParam("token") String token) {
        String resultado = authService.validarTokenRecuperacion(token);

        switch (resultado) {
            case "valido": {
                var body = new AuthResponseDto(null, AuthStatus.TOKEN_RECUPERACION_VALIDO, "Token válido.");
                return ResponseEntity.status(HttpStatus.OK).body(body);
            }
            case "expired": {
                var body = new AuthResponseDto(null, AuthStatus.TOKEN_RECUPERACION_EXPIRADO,
                        "El link de recuperación venció. Pedí uno nuevo.");
                return ResponseEntity.status(HttpStatus.GONE).body(body);
            }
            default: {
                var body = new AuthResponseDto(null, AuthStatus.TOKEN_RECUPERACION_INVALIDO,
                        "El link de recuperación no es válido.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
        }
    }

    @PostMapping("/restablecerContrasenia")
    public ResponseEntity<AuthResponseDto> restablecerContrasenia(@RequestBody RestablecerContraseniaRequestDto datos) {
        try {
            authService.restablecerContrasenia(datos.token(), datos.nuevaContrasenia());

            var body = new AuthResponseDto(null, AuthStatus.CONTRASENIA_RESTABLECIDA,
                    "Tu contraseña se actualizó correctamente. Ya podés iniciar sesión.");
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } catch (IllegalArgumentException e) {
            var body = new AuthResponseDto(null, AuthStatus.TOKEN_RECUPERACION_INVALIDO, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

}
