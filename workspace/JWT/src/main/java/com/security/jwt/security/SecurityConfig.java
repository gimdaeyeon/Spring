package com.security.jwt.security;

import com.security.jwt.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final TokenProvider tokenProvider;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // PasswordEncoder는 BCryptPasswordEncoder를 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
//                세션리스 설정
                .sessionManagement(sessionManagement-> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))


                .authorizeHttpRequests((requests) -> requests
//                        해당  url로 들어온 요청은 인증을 해야한다
                                .requestMatchers("/main/hello").authenticated()
//                        그 밖의 요청은 허용한다.
                                .requestMatchers("/admin/admin")
                                .hasAnyRole("USER", "ADMIN")
                                .anyRequest().permitAll()

                )
//                jwt는 sessionLess방식이기 때문에 스프링 시큐맅티에서 기본적으로 제공하는
//                formLogin과 logout을 따로 설정해줄 필요 없다.
//                .formLogin((form) -> form
////                        로그인 페이지의 url설정
//                                .loginPage("/user/login")
//                                .loginProcessingUrl("/user/login")
//                                .defaultSuccessUrl("/")
////                                .successHandler(new CustomLoginSuccessHandler("/"))
//                )
                .exceptionHandling(exceptionHandling->
                        exceptionHandling.accessDeniedPage("/")
                );


        return httpSecurity.build();
    }

}