package com.tortas.decoradas.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

        private final AuthenticationEntryPoint authenticationEntryPoint;
        private final JWTAuthenticationFilter jwtAuthenticationFilter;

        public SecurityConfig(AuthenticationEntryPoint authenticationEntryPoint,
                        JWTAuthenticationFilter jwtAuthenticationFilter) {
                this.authenticationEntryPoint = authenticationEntryPoint;
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(AbstractHttpConfigurer::disable)
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests(authorize -> authorize

                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                                .requestMatchers(HttpMethod.POST, "/api/producto/lista/**").hasAnyAuthority("ADMIN")

                                                .requestMatchers(HttpMethod.POST, "/api/producto/listaCategoriasSabor/**").hasAnyAuthority("ADMIN")

                                                .requestMatchers(HttpMethod.POST, "/api/producto/listaBizcochuelos/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/producto/listaTamanos/**").hasAnyAuthority("ADMIN")

                                                .requestMatchers(HttpMethod.POST, "/api/producto/listaCodigosPostales/**").hasAnyAuthority("ADMIN")

                                                .requestMatchers(HttpMethod.PUT, "/api/producto/*").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/producto/*").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.PATCH, "/api/producto/*/estado").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/producto/ajustarPrecios").hasAnyAuthority("ADMIN")
                                                .requestMatchers("/api/producto/**").permitAll()
                                                .requestMatchers("/api/productos/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/registrarProducto/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/tipoUno/{tipoV}/idUno/{idV}/tipoDos/{tipoN}/segundo/{idN}/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/cambiar/{tipo}/id/{idb}/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers("/api/producto/nombre/**").permitAll()
                                                .requestMatchers("/api/categoriaEspecial/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/auth/verificarAdmin").hasAnyAuthority("ADMIN")
                                                .requestMatchers("/api/auth/registrar/**").permitAll()
                                                .requestMatchers("/api/auth/login/**").permitAll()
                                                .requestMatchers("/api/categoriaSinEspecial/**").permitAll()
                                                .requestMatchers("/api/auth/verifyEmail/**").permitAll()
                                                .requestMatchers("/api/auth/resendVerificationToken/**").permitAll()
                                                .requestMatchers("/api/auth/recuperarContrasenia/**").permitAll()
                                                .requestMatchers("/api/auth/validarTokenRecuperacion/**").permitAll()
                                                .requestMatchers("/api/auth/restablecerContrasenia/**").permitAll()
                                                .requestMatchers("/api/listaProductos/**").permitAll()
                                                .requestMatchers("/api/categorias").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/pedidosPor").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/pagos/webhook").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/configuracion/cupoMaximoPorDia").permitAll()
                                                .requestMatchers(HttpMethod.PUT, "/api/configuracion/cupoMaximoPorDia").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/configuracion/diasBloqueados").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/configuracion/diasBloqueados").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/configuracion/diasBloqueados/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/configuracion/pedidosManuales").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/configuracion/pedidosManuales").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/configuracion/pedidosManuales/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/pedidos/porFecha/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.PATCH, "/api/pedidos/*/entregar").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/pedidos/*/factura").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/facturas/existentes").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/pedidos/*/nota-credito").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/notas-credito/existentes").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.PATCH, "/api/upload/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/carrusel/**").permitAll()
                                                .requestMatchers(HttpMethod.PUT, "/api/carrusel/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/carrusel/**").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/usuarios").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.PATCH, "/api/usuarios/*/rol").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.PATCH, "/api/usuarios/*/estado").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/registrar").denyAll()
                                                .requestMatchers(HttpMethod.GET, "/api/pedidos").hasAnyAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/api/email/**").hasAnyAuthority("ADMIN")
                                                .anyRequest().authenticated())
                                .exceptionHandling(
                                                exceptionConfig -> exceptionConfig
                                                                .authenticationEntryPoint(authenticationEntryPoint)
                                )
                                .sessionManagement(
                                                sessionConfig -> sessionConfig
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                )
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                                .build();
        }
}
