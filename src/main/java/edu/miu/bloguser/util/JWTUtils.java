package edu.miu.bloguser.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret.salt}")
    private String jwtSecretSalt;

    @Value("${jwt.secret.token.issuer}")
    private String tokenIssuer;

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setIssuer(tokenIssuer)
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(60 * 60 * 5)))
                .signWith(Keys.hmacShaKeyFor(jwtSecretSalt.getBytes())).compact();
    }

    public String parseToken(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecretSalt.getBytes()))
                    .build();
            Claims claims = parser.parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            throw new RuntimeException("Token is invalid !");
        }
    }
}
