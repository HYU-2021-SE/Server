package com.dionysos.winecellar.domain.auth.infra;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dionysos.winecellar.domain.auth.exception.InvalidJwtTokenException;
import com.dionysos.winecellar.domain.member.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
    private final Key key;
    private final long expire;

    public JwtTokenProvider(@Value("${jwt.token.key}") String key, @Value("${jwt.token.expire-length}") long expire) {
        this.key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(key.getBytes()));
        this.expire = expire;
    }

    public String createToken(Member member) {
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
    }

    public String getMemberEmail(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        return jwtParser.parseClaimsJws(token).getBody().get("memberEmail", String.class);
    }

    public boolean validateToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
            Jws<Claims> claims = jwtParser.parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
