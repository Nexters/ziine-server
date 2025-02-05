package com.ziine.admin.presentation;

import com.ziine.admin.application.AdminContextHolder;
import com.ziine.admin.application.JwtService;
import com.ziine.admin.domain.Admin;
import com.ziine.admin.exception.AdminUnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {  // TODO. 추후 Annotation과 Aop를 활용하여 개선

    private static final String AUTHORIZATION_HTTP_HEADER = "Authorization";

    private final JwtService jwtService;

    @Override
    public boolean preHandle(
        final HttpServletRequest httpServletRequest,
        final HttpServletResponse httpServletResponse,
        final Object handler
    ) {
        final String authorizationHttpHeader = httpServletRequest.getHeader(AUTHORIZATION_HTTP_HEADER);
        final Admin admin = jwtService.extractAdminFromJwtToken(authorizationHttpHeader)
            .orElseThrow(() -> AdminUnauthorizedException.INSTANCE);
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
