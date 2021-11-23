package com.dionysos.winecellar.auth.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.dionysos.winecellar.member.domain.Member;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService {
    private final Key key;
    private final long expire;

    public JwtTokenService(@Value("${jwt.token.key}") String key, @Value("${jwt.token.expire}") long expire) {
        this.key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(key.getBytes()));
        this.expire = expire;
    }

    public String creatToken(Member member) {
        Date now = new Date();
        Date validity = new Date(now.getTime()
            + expire);

        return Jwts.builder()
            .claim("memberId", member.getMemberId())
            .claim("memberEmail", member.getEmail())
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Long getMemberId(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        return jwtParser.parseClaimsJws(token).getBody().get("memberId", Long.class);
        // return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public String getMemberEmail(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        return jwtParser.parseClaimsJws(token).getBody().get("memberEmail", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
