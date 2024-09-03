package com.security.jwt2.security;

import com.security.jwt2.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter { // OncePerRequestFilter -> 한 번 실행 보장

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
//    Jwt 토큰 검증 필터 수행
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

//        Jwt가 헤더에 있는 경우
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
//            jwt 유효성 검증
            if(jwtUtil.validateToken(token)){
                String loginId = jwtUtil.getUserLoginId(token);

                // 유저와 토큰 일치 시 userDetails 생성
                UserDetails userDetails = userService.loadUserByUsername(loginId);
                if(userDetails != null){
//                UserDetails, password, Role -> 접근권한 인증 Token 생성
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

//                  현재 Request의 Serurity Context에 접근권한 설정
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);// 다음 필터로 넘기기
    }
}














