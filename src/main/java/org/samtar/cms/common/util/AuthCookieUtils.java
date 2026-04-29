package org.samtar.cms.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AuthCookieUtils {
    private static String refreshTokenName;
    private static long expiration;
    private static String path;
    private static boolean httpOnly;
    private static boolean secure;
    private static String sameSite;

    public AuthCookieUtils(
            @Value("${cookie.refreshTokenName}") String refreshTokenName,
            @Value("${cookie.expiration}") long expiration,
            @Value("${cookie.path}") String path,
            @Value("${cookie.httpOnly}") boolean httpOnly,
            @Value("${cookie.secure}") boolean secure,
            @Value("${cookie.sameSite}") String sameSite) {
        AuthCookieUtils.refreshTokenName = refreshTokenName;
        AuthCookieUtils.expiration = expiration;
        AuthCookieUtils.path = path;
        AuthCookieUtils.httpOnly = httpOnly;
        AuthCookieUtils.secure = secure;
        AuthCookieUtils.sameSite = sameSite;
    }

    public static ResponseCookie addRefreshToken(String token) {
        return ResponseCookie.from(refreshTokenName, token)
                .httpOnly(httpOnly)
                .secure(secure)
                .sameSite(sameSite)
                .path(path)
                .maxAge(Duration.ofMillis(expiration))
                .build();
    }

    public static ResponseCookie clearRefreshToken() {
        return ResponseCookie.from(refreshTokenName, "")
                .httpOnly(httpOnly)
                .secure(secure)
                .sameSite(sameSite)
                .path(path)
                .maxAge(Duration.ZERO)
                .build();
    }
}
