package org.ryan.infrastructure;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.ryan.constant.ResponseCode;
import org.ryan.exception.SocialMonoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtil {
    @Value("${application.security.tokenSecret}")
    private String tokenSecret;

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getId);
    }

    public Object getUserIdFromToken(String token) {
        Claims claims = getClaims(token);
        return Optional.ofNullable(claims.get("userId"))
                .orElseThrow(() -> new SocialMonoException(ResponseCode.NOT_FOUND));
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
