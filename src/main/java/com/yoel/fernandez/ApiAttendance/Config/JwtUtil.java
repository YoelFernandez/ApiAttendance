package com.yoel.fernandez.ApiAttendance.Config;

import java.util.Date;
import java.security.Key;
import java.util.Base64;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 🔹 Genera una clave segura

    public String generateToken(String username) {
        try {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                    .signWith(SECRET_KEY)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token JWT", e);
        }
    }
}
