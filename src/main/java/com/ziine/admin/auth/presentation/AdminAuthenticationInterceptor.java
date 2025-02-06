package com.ziine.admin.auth.presentation;

import com.ziine.admin.auth.application.AdminContextHolder;
import com.ziine.admin.auth.application.JwtService;
import com.ziine.admin.auth.application.exception.AdminUnauthorizedException;
import com.ziine.admin.auth.domain.Admin;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {  // TODO. 추후 Annotation과 AOP를 활용하여 개선

    private static final String AUTHORIZATION_HTTP_HEADER = "Authorization";

    private final JwtService jwtService;

    @Override
    public boolean preHandle(
        final HttpServletRequest httpServletRequest,
        final HttpServletResponse httpServletResponse,
        final Object handler
    ) {
        final String authorizationHttpHeader = httpServletRequest.getHeader(AUTHORIZATION_HTTP_HEADER);
        final Claims claims = jwtService.extractClaimsFromJwtToken(authorizationHttpHeader)
            .orElseThrow(() -> AdminUnauthorizedException.INSTANCE);

        final Admin admin = Admin.fromClaims(claims);
        AdminContextHolder.setAdmin(admin);

        return true;
    }

    @Override
    public void afterCompletion(
        final HttpServletRequest httpServletRequest,
        final HttpServletResponse httpServletResponse,
        final Object handler,
        final Exception exception
    ) {
        AdminContextHolder.clear();
    }
}
