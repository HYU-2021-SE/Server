package com.dionysos.winecellar.auth.service;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.auth.dto.AuthResponseDto;
import com.dionysos.winecellar.auth.dto.LoginDto;

@Service
public class AuthService {
    public AuthResponseDto login(LoginDto loginDto) {
        return new AuthResponseDto("token");
    }
}
