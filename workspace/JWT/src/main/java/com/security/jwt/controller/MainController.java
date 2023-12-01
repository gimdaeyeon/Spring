package com.security.jwt.controller;

import com.security.jwt.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.crypto.SecretKey;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/")
    public String home(HttpServletRequest request){
//        System.out.println(request.getHeader("accessToken"));
        return "main/home";
    }
    @GetMapping("/main/hello")
    public String hello(HttpServletRequest req){
        return "main/hello";
    }
    @GetMapping("/admin/admin")
    public String admin(){
        return "admin/admin";
    }
}
