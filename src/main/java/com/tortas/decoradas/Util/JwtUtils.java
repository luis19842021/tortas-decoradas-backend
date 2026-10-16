package com.tortas.decoradas.Util;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import javax.crypto.SecretKey;

import org.apache.commons.lang3.time.DateUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
  private static volatile SecretKey secretKey;

  private static final String ISSUER = "server";

  private JwtUtils() {
  }

  public static void configurarClave(String claveBase64) {
    if (claveBase64 == null || claveBase64.isBlank()) {
      throw new IllegalStateException(
          "Falta la clave de los JWT: definí la propiedad jwt.secret (o la variable de entorno JWT_SECRET).");
    }
    secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(claveBase64.trim()));
  }

  private static SecretKey clave() {
    if (secretKey == null) {
      throw new IllegalStateException("La clave de los JWT todavía no fue configurada (ver JwtConfig).");
    }
    return secretKey;
  }

  public static boolean validateToken(String jwtToken) {
    return parseToken(jwtToken).isPresent();
  }

  private static Optional<Claims> parseToken(String jwtToken) {
    var jwtParser = Jwts.parser()
        .verifyWith(clave())
        .build();

    try {
      return Optional.of(jwtParser.parseSignedClaims(jwtToken).getPayload());
    } catch (JwtException | IllegalArgumentException e) {
      System.err.println("JWT Exception occurred: " + e.getMessage());
    }

    return Optional.empty();
  }

  public static Optional<String> getUsernameFromToken(String jwtToken) {
    var claimsOptional = parseToken(jwtToken);
    return claimsOptional.map(Claims::getSubject);
  }

  public static String generateToken(String username) {
    return generateToken(username, null);
  }

  public static String generateToken(String username, String nombre) {
    return generateToken(username, nombre, false);
  }

  public static String generateToken(String username, String nombre, boolean esAdmin) {

    var currentDate = new Date();

    var jwtExpirationInMinutes = 60 * 24;

    var expiration = DateUtils.addMinutes(currentDate, jwtExpirationInMinutes);

    var builder = Jwts.builder()
        .id(UUID.randomUUID().toString())
        .issuer(ISSUER)
        .subject(username);

    if (nombre != null) {
      builder.claim("nombre", nombre);
    }
    builder.claim("esAdmin", esAdmin);

    return builder
        .signWith(clave())
        .issuedAt(currentDate)
        .expiration(expiration)
        .compact();
  }
}
