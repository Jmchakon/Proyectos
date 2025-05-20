package com.tuempresa.sistemadereservas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
public class JwtUtil {

    private static final String SECRET_KEY = "ak9D83nskgHJSJ28gLskf7@D!3"; // 🔥 Una contraseña secreta para firmar los tokens.

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                     // 📌 Aquí ponemos quién es el usuario (su nombre)
                .setIssuedAt(new Date())                  // 📆 Fecha de creación del token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // ⏳ Expira en 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 🔏 Firma con nuestra clave secreta
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();  // 🎯 Extrae el nombre de usuario del token
    }

    public boolean validateToken(String token, String username) {
        final String usernameFromToken = extractUsername(token);
        return (usernameFromToken.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());  // ✅ Si la fecha de expiración ya pasó, está expirado
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)           // 🔐 Usa la misma clave secreta para descifrar
                .parseClaimsJws(token)
                .getBody();
    }
}
