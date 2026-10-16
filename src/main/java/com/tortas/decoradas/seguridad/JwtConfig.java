package com.tortas.decoradas.seguridad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.tortas.decoradas.Util.JwtUtils;

@Configuration
public class JwtConfig {

    public JwtConfig(@Value("${jwt.secret:}") String claveBase64) {
        JwtUtils.configurarClave(claveBase64);
    }
}
