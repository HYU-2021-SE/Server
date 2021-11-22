package com.dionysos.winecellar.auth.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionysos.winecellar.auth.dto.AuthResponseDto;
import com.dionysos.winecellar.auth.dto.LoginDto;
import com.dionysos.winecellar.auth.dto.SocialAccessTokenDto;
import com.dionysos.winecellar.auth.service.AuthService;
import com.dionysos.winecellar.auth.service.SocialRequestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final SocialRequestService socialRequestService;
    private final AuthService authService;

    @GetMapping("/api/auth/login")
    public void requestAuth(HttpServletResponse httpServletResponse) throws IOException {
        socialRequestService.requestAuthCode(httpServletResponse);
    }

    @GetMapping("/api/auth/callback")
    public ResponseEntity<AuthResponseDto> login(@RequestParam String code) throws
        IOException,
        InterruptedException {
        SocialAccessTokenDto socialAccessTokenDto = socialRequestService.requestSocialAccessToken(code);
        LoginDto loginDto = socialRequestService.requestSocialUser(socialAccessTokenDto);

        AuthResponseDto authResponseDto = authService.login(loginDto);
        return ResponseEntity.ok(authResponseDto);
    }
}
