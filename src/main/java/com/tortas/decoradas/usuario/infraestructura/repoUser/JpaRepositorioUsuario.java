package com.tortas.decoradas.usuario.infraestructura.repoUser;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tortas.decoradas.usuario.infraestructura.entiUser.EntidadUsuario;

public interface JpaRepositorioUsuario extends JpaRepository<EntidadUsuario,Long>  {

    Optional <EntidadUsuario> findByEmail(String email);

    Page<EntidadUsuario> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String nombre, String apellido, String email, Pageable pageable);
}
