package ru.weber.auth.service.service.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.weber.auth.service.dto.TokenDto;

import java.security.Key;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtService {

    private final JwtKeyProvider jwtKeyProvider;

    @Value("${app.public-key}")
    private String publicKey;

    public TokenDto verifyTokenAndCreateTokenDto(String token) {
        Claims claims = verifyTokenAndGetClaims(token);
        return new TokenDto(
                claims.get("firstName", String.class),
                claims.get("lastName", String.class),
                claims.get("middleName", String.class),
                claims.getExpiration().toInstant(),
                token
        );
    }

    private Claims verifyTokenAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKeyResolver(getSigningKeyResolver())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SigningKeyResolver getSigningKeyResolver() {
        return new SigningKeyResolverAdapter() {
            @Override
            public Key resolveSigningKey(JwsHeader header, Claims claims) {
                return jwtKeyProvider.readKey(publicKey);
            }
        };
    }
}
