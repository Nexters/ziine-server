package com.ziine.admin.auth.application;

import com.ziine.global.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(JwtProperties.class)
public class JwtService {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    private final SecretKey jwtSecretKey;

    public JwtService(final JwtProperties jwtProperties) {
        this.jwtSecretKey = new SecretKeySpec(
            jwtProperties.getSecretKey()
                .getBytes(StandardCharsets.UTF_8),
            Jwts.SIG.HS256.key()
                .build()
                .getAlgorithm()
        );
    }

    public Optional<Claims> extractClaimsFromJwtToken(final String authorizationHeader) {
        try {
            final String jwtToken = removeBearerPrefix(authorizationHeader);
            return Optional.of(parseJwtClaims(jwtToken));
        } catch (final Exception exception) {
            return Optional.empty();
        }
    }

    private String removeBearerPrefix(final String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_TOKEN_PREFIX)) {
            return authorizationHeader.substring(BEARER_TOKEN_PREFIX.length());
        }
        return authorizationHeader;
    }

    private Claims parseJwtClaims(final String jwtToken) {
        return Jwts.parser()
            .verifyWith(jwtSecretKey)
            .build()
            .parseSignedClaims(jwtToken)
            .getPayload();
    }
}
