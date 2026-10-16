package com.tortas.decoradas.usuario.infraestructura.controlUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.tortas.decoradas.usuario.dominio.modelo.ActualizarPerfilRequestDto;
import com.tortas.decoradas.usuario.dominio.modelo.CambiarContraseniaRequestDto;
import com.tortas.decoradas.usuario.dominio.modelo.CambiarEstadoRequestDto;
import com.tortas.decoradas.usuario.dominio.modelo.CambiarRolRequestDto;
import com.tortas.decoradas.usuario.dominio.modelo.PaginaUsuarios;
import com.tortas.decoradas.usuario.dominio.modelo.Usuario;
import com.tortas.decoradas.usuario.aplicacion.servicio.ServicioUsuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")

public class ControladorUsuario {

    private final ServicioUsuario servicioUsuario;

    public ControladorUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping("registrar")

    public ResponseEntity<Usuario> crearusuario(@RequestBody Usuario usuario) {
        Usuario crearusuario = servicioUsuario.crearUsuario(usuario);

        return new ResponseEntity<>(crearusuario, HttpStatus.CREATED);
    }

    @GetMapping("usuarios")
    public ResponseEntity<PaginaUsuarios> obtenerUsuariosPaginado(
            @RequestParam(required = false) String busqueda,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio) {
        PaginaUsuarios paginaUsuarios = servicioUsuario.obtenerUsuariosPaginado(busqueda, pagina, tamanio);
        return new ResponseEntity<>(paginaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable String email) {
        return servicioUsuario.buscarPorEmail(email)
        .map(usuario->new ResponseEntity<>(usuario,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usuarios/mi-perfil")
    public ResponseEntity<Usuario> obtenerMiPerfil(Authentication authentication) {
        String email = authentication.getName();
        return servicioUsuario.buscarPorEmail(email)
        .map(usuario->new ResponseEntity<>(usuario,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/usuarios/mi-perfil")
    public ResponseEntity<Usuario> actualizarMiPerfil(Authentication authentication,
            @RequestBody ActualizarPerfilRequestDto datos) {
        String email = authentication.getName();
        Usuario usuario = servicioUsuario.actualizarPerfil(email, datos);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PatchMapping("/usuarios/mi-perfil/contrasenia")
    public ResponseEntity<Void> cambiarMiContrasenia(Authentication authentication,
            @RequestBody CambiarContraseniaRequestDto datos) {
        String email = authentication.getName();
        servicioUsuario.cambiarContrasenia(email, datos.getContraseniaActual(), datos.getContraseniaNueva());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/usuarios/{id}/rol")
    public ResponseEntity<Usuario> cambiarRolUsuario(@PathVariable Long id, @RequestBody CambiarRolRequestDto datos,
            Authentication authentication) {
        String emailAdminQueOpera = authentication.getName();
        Usuario usuario = servicioUsuario.cambiarRol(id, datos.getRol(), emailAdminQueOpera);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PatchMapping("/usuarios/{id}/estado")
    public ResponseEntity<Usuario> cambiarEstadoUsuario(@PathVariable Long id, @RequestBody CambiarEstadoRequestDto datos,
            Authentication authentication) {
        String emailAdminQueOpera = authentication.getName();
        Usuario usuario = servicioUsuario.cambiarEstado(id, datos.isSuspendida(), emailAdminQueOpera);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
