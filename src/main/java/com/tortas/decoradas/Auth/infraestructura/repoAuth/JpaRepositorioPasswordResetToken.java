package com.tortas.decoradas.Auth.infraestructura.repoAuth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.Auth.infraestructura.entiAuth.EntidadPasswordResetToken;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

public interface JpaRepositorioPasswordResetToken extends JpaRepository<EntidadPasswordResetToken, Long> {
    EntidadPasswordResetToken findByToken(String token);

    EntidadPasswordResetToken findByUsuario(EntidadUsuario usuario);
}
