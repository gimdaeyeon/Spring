package com.security.jwt2.security;

import com.security.jwt2.security.jwt.JwtAuthenticationFilter;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.accessSecret}")
    String accessSecret;
    @Value("${jwt.refreshSecret}")
    String refreshSecret;
    @Bean("accessSecret")
    public SecretKey createAccessSecret(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
    }
    @Bean("refreshSecret")
    public SecretKey createRefreshSecret(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
//                세션리스 설정
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
//                        해당  url로 들어온 요청은 인증을 해야한다
                                .requestMatchers("/main/hello").authenticated()
//                        그 밖의 요청은 허용한다.
                                .requestMatchers("/admin/admin")
                                .hasAnyAuthority( "ADMIN")
                                .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling
                            .authenticationEntryPoint((req, resp, authException) ->  //유효한 자격이 없는 상태에서 접근 할 때
                                    resp.sendRedirect("/user/login"));   // (로그인이 되어있지 않을 때)

                    exceptionHandling.accessDeniedHandler((req, resp, accessDeniedException) -> // 필요한 권한이 없는 상태에서 접근할 때
                            resp.sendRedirect("/"));
//                    exceptionHandling.accessDeniedPage("/main/home");   //에러 발생시 이동할 url
                });

        return httpSecurity.build();
    }

}