package org.samtar.cms.common.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.samtar.cms.common.exception.AuthException;
import org.samtar.cms.modules.shared.enums.TokenTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HexFormat;
import java.util.Map;

@Component
public class JwtUtils {
    private final SecretKey accessSecret;
    private final SecretKey refreshSecret;
    private final long accessTimer;
    private final long refreshTimer;

    public JwtUtils(
            @Value("${jwt.accessSecret}") String accessTokenSecret,
            @Value("${jwt.refreshSecret}") String refreshTokenSecret,
            @Value("${jwt.expiration}") Long accessTimer,
            @Value("${jwt.refreshExpiration}") Long refreshTimer


    ) {
        this.accessSecret = generateByte(accessTokenSecret);
        this.refreshSecret = generateByte(refreshTokenSecret);
        this.accessTimer = accessTimer;
        this.refreshTimer = refreshTimer;
    }

    public String generateToken(Map<String, Object> data, TokenTypes type) {
        SecretKey secretKey = type == TokenTypes.Access ? this.accessSecret : this.refreshSecret;
        long tokenExpiry = type == TokenTypes.Access ? this.accessTimer : refreshTimer;
        return Jwts
                .builder()
                .signWith(secretKey)
                .claims(data)
                .expiration(new Date(System.currentTimeMillis() + tokenExpiry))
                .issuedAt(new Date()).compact();
    }

    public Claims decodeToken(String token, TokenTypes type)  {
        SecretKey secretKey = type == TokenTypes.Access ? this.accessSecret : this.refreshSecret;
        try {
            return this.parserClaims(token, type, secretKey);
        } catch (ExpiredJwtException exp) {
            throw AuthException.tokenExpired(type);
        } catch (JwtException err) {
            throw AuthException.InvalidToken(type);
        }
    }


    private Claims parserClaims(String token, TokenTypes type, SecretKey secret)  {
        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey generateByte(String hexKey) {
        byte[] secret = HexFormat.of().parseHex(hexKey.trim());
        return Keys.hmacShaKeyFor(secret);
    }

}
