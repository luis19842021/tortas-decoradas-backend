package com.tortas.decoradas.Auth.aplicacion.servicio;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tortas.decoradas.Auth.infraestructura.repoAuth.JpaRepositorioAuth;
import com.tortas.decoradas.rol.infraestructura.entiRol.EntidadRol;
@Component
public class UserDetailsServiceImpl implements UserDetailsService{

     private JpaRepositorioAuth userRepo;

        public UserDetailsServiceImpl(JpaRepositorioAuth userRepo) {
                this.userRepo = userRepo;
        }

    public Collection<GrantedAuthority> mapToAuthorities(List<EntidadRol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return User.builder()
                .username(user.getEmail())
                .password(user.getContraseña())
                .authorities(mapToAuthorities(user.getRoles()))
                .disabled(user.isSuspendida())
            .build();
    }
}
