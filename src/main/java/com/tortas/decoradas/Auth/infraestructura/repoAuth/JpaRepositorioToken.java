package com.tortas.decoradas.Auth.infraestructura.repoAuth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.Auth.infraestructura.entiAuth.EntidadTokentemp;
import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

public interface JpaRepositorioToken  extends JpaRepository<EntidadTokentemp, Long>{
          EntidadTokentemp findByToken(String token);

          EntidadTokentemp findByUsuario(EntidadUsuario usuario);

}
