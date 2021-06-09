package br.edu.ifce.backend.adpters.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean tokenIsValid(String token) {
        var claims = getClaimsToken(token);

        if (claims != null) {
            var username = claims.getSubject();
            var expirationDate = claims.getExpiration();
            var currentDate = new Date(System.currentTimeMillis());

            System.out.println(username);
            System.out.println(expirationDate);
            System.out.println(currentDate);

            return username != null && expirationDate != null && currentDate.before(expirationDate);
        }

        return false;
    }

    public String getUsername(String token) {
        var claims = getClaimsToken(token);

        if (claims != null) {
            return claims.getSubject();
        }

        return null;
    }

    private Claims getClaimsToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
