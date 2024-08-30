package com.security.jwt2.security;

import com.security.jwt2.domain.dto.user.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration_time}") long accessTokenExpTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

//    Access Token 생성
    public String createAccessToken(UserDto user){
        return null;
    }

//    Jwt 생성
    public String createToken(UserDto user,long expireTime){
        return null;
    }

//    토큰에서 UserId 추출
    public String getUserId(String token){
        return null;
    }

//    Jwt 검증
    public boolean validateToken(String token){
        return false;
    }

//    Jwt Claims 추출
    public Claims parseClaims(String accessToken){
        return null;
    }


}










