package com.jwt.userJwt.configuration;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private String SECRET_KEY = "secret";

    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
    }
}
