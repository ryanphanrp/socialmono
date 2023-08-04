package org.learning.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learning.domain.AuthUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    @Value("${application.security.tokenSecret}")
    private String tokenSecret;

    @Value("${application.security.accessTokenExpiration}")
    private Long accessTokenExpiration;

    @Value("${application.security.refreshTokenExpiration}")
    private Long refreshTokenExpiration;

    public String createToken(AuthUser authUserDetails) {
        return buildToken(new HashMap<>(), authUserDetails.getUsername(), accessTokenExpiration);
    }

    public String createRefreshToken(AuthUser authUserDetails) {
        return buildToken(new HashMap<>(), authUserDetails.getUsername(), refreshTokenExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, String username, Long expiration) {
        Date now = new Date();
        Date tokenExpiration = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder().setClaims(extraClaims).setId(username).setSubject(username).setIssuedAt(now).setExpiration(tokenExpiration).signWith(toSigningKey(), SignatureAlgorithm.HS512).compact();
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getId);
    }

    public boolean isValid(String token) {
        if (Objects.isNull(token)) return false;

        try {
            makeJwtParser().parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    // get claims token
    private Claims getClaims(String token) {
        return makeJwtParser().parseClaimsJws(token).getBody();
    }

    // extract claims from token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }


    // Make JWT Parser
    private JwtParser makeJwtParser() {
        return Jwts.parserBuilder().setSigningKey(toSigningKey()).build();
    }

    // Make Sign Key with HMAC
    private Key toSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(tokenSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
