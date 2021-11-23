package com.dionysos.winecellar.auth.service;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.auth.dto.AuthResponseDto;
import com.dionysos.winecellar.auth.dto.LoginDto;
import com.dionysos.winecellar.auth.infra.JwtTokenProvider;
import com.dionysos.winecellar.member.domain.Member;
import com.dionysos.winecellar.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    public AuthResponseDto login(LoginDto loginDto) {
        Member member = memberService.findOrElseSave(loginDto);
        String token = jwtTokenProvider.creatToken(member);
        return new AuthResponseDto(token);
    }
}
