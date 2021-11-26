package com.dionysos.winecellar.domain.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.dionysos.winecellar.domain.auth.exception.InvalidJwtTokenException;
import com.dionysos.winecellar.domain.auth.infra.JwtTokenProvider;
import com.dionysos.winecellar.domain.auth.infra.TokenExtractor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenExtractor tokenExtractor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = tokenExtractor.extract(request, "bearer");
        System.out.println(token);
        if (!jwtTokenProvider.validateToken(token)) {
            throw new InvalidJwtTokenException("Invalid token");
        }
        Long id = jwtTokenProvider.getMemberId(token);
        request.setAttribute("loginMemberId", id);
        return true;
    }
}
