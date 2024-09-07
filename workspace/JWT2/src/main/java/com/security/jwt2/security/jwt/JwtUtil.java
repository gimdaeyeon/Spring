package com.security.jwt2.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.expiration_time}")
    private long accessTokenExpTime;
    private final SecretKey secretKey;


    //    Access Token 생성
    public String createAccessToken(String loginId) {
        return createToken(loginId, accessTokenExpTime);
    }

    //    Jwt 생성
    private String createToken(String loginId, long expireTime) {
        return Jwts.builder()
                .subject(loginId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(secretKey)
                .compact();
    }

    //    토큰에서 UserId 추출
    public String getUserLoginId(String token) {
        return parseClaims(token).getSubject();
    }

    //    Jwt 검증
    public boolean isValidToken(String token) {
        return parseClaims(token)
                .getExpiration()
                .before(new Date());
    }

    //    Jwt Claims 추출
    public Claims parseClaims(String accessToken) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(3 * 60)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
    }


}










