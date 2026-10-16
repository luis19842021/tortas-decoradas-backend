package com.tortas.decoradas.usuario.aplicacion.CasoDeUsoUsuario;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.dominio.puerto.in.CasoDeUsoCambiarContrasenia;
import com.tortas.decoradas.usuario.dominio.puerto.out.PuertoRepositorioUsuario;

public class CasoDeUsoCambiarContraseniaImpl implements CasoDeUsoCambiarContrasenia {

    private final PuertoRepositorioUsuario puertoRepositorioUsuario;
    private final PasswordEncoder passwordEncoder;

    public CasoDeUsoCambiarContraseniaImpl(PuertoRepositorioUsuario puertoRepositorioUsuario,
            PasswordEncoder passwordEncoder) {
        this.puertoRepositorioUsuario = puertoRepositorioUsuario;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void cambiarContrasenia(String email, String contraseniaActual, String contraseniaNueva) {
        if (contraseniaActual == null || contraseniaActual.isBlank()) {
            throw new IllegalArgumentException("Falta la contraseña actual.");
        }
        if (contraseniaNueva == null || contraseniaNueva.length() < 6) {
            throw new IllegalArgumentException("La contraseña nueva tiene que tener al menos 6 caracteres.");
        }

        Usuario usuario = puertoRepositorioUsuario.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        if (!passwordEncoder.matches(contraseniaActual, usuario.getContraseña())) {
            throw new IllegalArgumentException("La contraseña actual no es correcta.");
        }

        usuario.setContraseña(passwordEncoder.encode(contraseniaNueva));
        puertoRepositorioUsuario.guardar(usuario);
    }
}
