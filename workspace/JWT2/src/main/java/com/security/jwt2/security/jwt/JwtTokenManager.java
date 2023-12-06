package com.security.jwt2.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenManager {

    private final SecretKey accessKey;
    private final SecretKey refreshKey;

    @Autowired
    public JwtTokenManager(@Qualifier("accessSecret") SecretKey accessKey,
                           @Qualifier("refreshSecret") SecretKey refreshKey) {
        this.accessKey = accessKey;
        this.refreshKey = refreshKey;
    }

    public String createAccessToken(String loginId, Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        return Jwts.builder()
                .claims()
                .subject(loginId)
                .add("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(now)
                .expiration(new Date(now.getTime() + JwtTokenType.ACCESS_TOKEN.getMaxAge()))
                .and()
                .signWith(accessKey)
                .compact();
    }

    public String createRefreshToken(String loginId) {
        Date now = new Date();
        return Jwts.builder()
                .claims()
                .subject(loginId)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + JwtTokenType.ACCESS_TOKEN.getMaxAge()))
                .and()
                .signWith(refreshKey)
                .compact();
    }
//        TODO 1. accessToken검증
//        TODO 2. refreshToken검증
//        TODO 3. accessToken에서  Authentication정보 가져오기


}
