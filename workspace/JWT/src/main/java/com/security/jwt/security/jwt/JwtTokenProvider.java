package com.security.jwt.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final SecretKey key;

    // 토큰 유효시간 30분
    private final long tokenValidTime = 30 * 60 * 1000L;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
//    }

    // JWT 토큰 생성
    public String createToken(String userPk, Collection<? extends GrantedAuthority> authorities) {

        Date now = new Date();
        return Jwts.builder()
                .claims()// 정보 저장
                    .subject(userPk)// JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
                    .add("authorities", authorities.stream().map(GrantedAuthority::getAuthority).toList())// 정보는 key / value 쌍으로 저장된다.
                    .issuedAt(now)// 토큰 발행 시간 정보
                    .expiration(new Date(now.getTime() + tokenValidTime))// 토큰 유효 시간
                .and()  //return back to the JwtBuilder
                .signWith(key)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        Claims payload = Jwts.parser().verifyWith(key)
                .clockSkewSeconds(60)   //1분
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)payload.get("authorities");

        System.out.println("authorities = " + authorities);

        return new UsernamePasswordAuthenticationToken(payload.getSubject(), "", authorities);
    }

    /**
     * https://github.com/jwtk/jjwt
     */

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        Jwts.parser().verifyWith(key);
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        String token = null;
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("accessToken")){
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().verifyWith(key)
                    .clockSkewSeconds(60)
                    .build().parseSignedClaims(jwtToken);
            return !claims.getPayload().getExpiration()
                    .before(new Date());

        } catch (Exception e) {
            return false;
        }
    }
}