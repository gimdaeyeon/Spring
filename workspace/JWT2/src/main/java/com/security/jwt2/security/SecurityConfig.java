package com.security.jwt2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

//                세션 관리 상태 없음으로 구성, SpringSecurity가 세션 생성 or 사용 X
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                formLogin, BasicHttp 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                .exceptionHandling(exceptionHandling -> {
                            exceptionHandling
                                    .authenticationEntryPoint((req, resp, authException) ->  //유효한 자격이 없는 상태에서 접근 할 때
                                            resp.sendRedirect("/user/login"));   // (로그인이 되어있지 않을 때)

                            exceptionHandling.accessDeniedHandler((req, resp, accessDeniedException) -> // 필요한 권한이 없는 상태에서 접근할 때
                                    resp.sendRedirect("/"));
                        }
                )
//                권한 규칙작성
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("*").permitAll()
//               @PreAuthrization을 사용할 것이기 때문에 모든 경로에 대한 인증처리는 Pass
                                .anyRequest().permitAll()
                )
        ;

        return httpSecurity.build();
    }

}