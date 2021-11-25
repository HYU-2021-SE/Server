package com.dionysos.winecellar.domain.auth.service;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.domain.auth.dto.AuthResponseDto;
import com.dionysos.winecellar.domain.auth.dto.LoginDto;
import com.dionysos.winecellar.domain.auth.infra.JwtTokenProvider;
import com.dionysos.winecellar.domain.member.domain.Member;
import com.dionysos.winecellar.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    public AuthResponseDto login(LoginDto loginDto) {
        Member member = memberService.findOrElseSave(loginDto);
        String token = jwtTokenProvider.createToken(member);
        return new AuthResponseDto(token);
    }
}
