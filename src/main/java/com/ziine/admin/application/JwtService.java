package com.ziine.admin.application;

import com.ziine.admin.application.config.JwtProperties;
import com.ziine.admin.domain.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
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

    public Optional<Admin> extractAdminFromJwtToken(final String authorizationHeader) {
        try {
            final String jwtToken = removeBearerPrefix(authorizationHeader);
            final Claims jwtClaims = parseJwtClaims(jwtToken);
            return Optional.of(convertJwtClaimsToAdmin(jwtClaims));
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

    private Admin convertJwtClaimsToAdmin(final Claims jwtClaims) {
        final String adminName = jwtClaims.get("name", String.class);
        return new Admin(adminName);
    }
}
