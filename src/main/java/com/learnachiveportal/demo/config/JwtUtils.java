package com.learnachiveportal.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class JwtUtils {

    private static String secret = "sdfsklfjsfjskfjsklfjsklfjsklfjskdfwiouweroiwurnkrwriouwruoirwepoeriwrwri";

    private static SecretKey getSecretKey() {
        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }

    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().claims(claims).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(9)))
                .issuer("HARSHIT")
                .signWith(getSecretKey())
                .subject(subject)
                .compact();
    }


    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, subject);
    }

    private Claims getClaims(String token) {

        token = token.trim();
        Process logger;
        System.out.println("Token before parsing: " + token);

        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();

    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();

    }

    public Object getHeader(String token, String key) {
        return getClaims(token).get(key);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    public Boolean validateToken(String token) {
        System.out.print("=============64=================="+token);
        return !isTokenExpired(token);
    }

    public String getRole(String token) {
        return getClaims(token).get("Role").toString();
    }
}
