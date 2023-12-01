package com.security.jwt.security;

import com.security.jwt.security.jwt.JwtAuthenticationFilter;
import com.security.jwt.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Value("${jwt.secret}")
    String secretKey;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecretKey createSecretKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return key;
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
//                내가 만든 JwtAuthenticationFilter를
//                UsernamePasswordAuthenticationFilter앞에 실행시키겠다
                .addFilterBefore(new JwtAuthenticationFilter(new JwtTokenProvider(createSecretKey())),
                        UsernamePasswordAuthenticationFilter.class)
//                jwt는 sessionLess방식이기 때문에 스프링 시큐맅티에서 기본적으로 제공하는
//                formLogin과 logout을 따로 설정해줄 필요 없다.
//                .formLogin((form) -> form
////                        로그인 페이지의 url설정
//                                .loginPage("/user/login")
//                                .loginProcessingUrl("/user/login")
//                                .defaultSuccessUrl("/")
////                                .successHandler(new CustomLoginSuccessHandler("/"))
//                )
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling
                            .authenticationEntryPoint((req, resp, authException) ->  //유효한 자격이 없는 상태에서 접근 할 때
                                    resp.sendRedirect("/user/login"));   // (로그인이 되어있지 않을 때)

                    exceptionHandling.accessDeniedHandler((req, resp, accessDeniedException) -> // 필요한 권한이 없는 상태에서 접근할 때
                            resp.sendRedirect("/"));  //403에러 발생
//                    exceptionHandling.accessDeniedPage("/main/home");   //에러 발생시 이동할 url
                });


        return httpSecurity.build();
    }

}