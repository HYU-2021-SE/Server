package com.dionysos.winecellar.domain.member.service;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.domain.auth.dto.LoginDto;
import com.dionysos.winecellar.domain.member.dao.MemberRepository;
import com.dionysos.winecellar.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findOrElseSave(LoginDto loginDto) {
        return memberRepository.findByEmail(loginDto.getEmail())
            .orElseGet(() -> memberRepository.save(
                Member.builder()
                    .email(loginDto.getEmail())
                    .build()
            ));
    }
}
