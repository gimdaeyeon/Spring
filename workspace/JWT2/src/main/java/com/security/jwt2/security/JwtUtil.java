package com.security.jwt2.security;

import com.security.jwt2.domain.dto.user.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.expiration_time}")
    private long accessTokenExpTime;
    private final SecretKey secretKey;


//    Access Token 생성
    public String createAccessToken(UserDto user){
        return createToken(user,accessTokenExpTime);
    }

//    Jwt 생성
    public String createToken(UserDto user,long expireTime){
        ZonedDateTime now = ZonedDateTime.now();
        return Jwts.builder()
                .subject(user.getLoginId())
                .issuedAt(Date.from(now.toInstant()))
                .signWith(secretKey)
                .expiration(Date.from(now.plusSeconds(expireTime).toInstant()))
                .compact();
    }

//    토큰에서 UserId 추출
    public String getUserLoginId(String token){
        return parseClaims(token).getSubject();
    }

//    Jwt 검증
    public boolean validateToken(String token){
        try {
            return parseClaims(token)
                    .getExpiration()
                    .before(new Date());
        } catch (SecurityException | MalformedJwtException e) {
            log.error("유효하지 않은 토큰",e);
        }
        return false;
    }

//    Jwt Claims 추출
    public Claims parseClaims(String accessToken){
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(3*60)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
    }


}










