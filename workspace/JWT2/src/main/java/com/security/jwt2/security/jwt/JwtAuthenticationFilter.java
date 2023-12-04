package com.security.jwt2.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
        * 1. 쿠키에서 엑세스토큰과 리프레시 토큰을 가져온다.
        * 2. 엑세스토큰이 유효한지 확인한다.
        * 3. 유효하다면 인증이 완료
        * 4. 유효하지 않다면 리프레시토큰을 확인한다.
        * 5 .리프레시 토큰이 유효하다면 엑세스 토큰과 리프레시토큰을 다시 발급하고 인증 완료
        * 6. 리프레시 토큰또한 유효하지 않다면 사용자가 다시 로그인할 수 있게 로그인페이지로 리다이렉트 시킨다.
        * */



        chain.doFilter(request, response);
    }
}
