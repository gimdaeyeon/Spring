package com.security.jwt.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.crypto.SecretKey;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final SecretKey secretKey;
    @GetMapping("/")
    public String home(HttpServletRequest request){
//        System.out.println(request.getHeader("accessToken"));
        return "main/home";
    }
    @GetMapping("/main/hello")
    public String hello(HttpServletRequest request){

        String token = null;

        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("accessToken")){
                token = cookie.getValue();
                break;
            }
        }
        Object authority = Jwts.parser().verifyWith(secretKey)
                .clockSkewSeconds(60L)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("authorities");

        System.out.println("authority = " + authority);
        return "main/hello";
    }
    @GetMapping("/admin/admin")
    public String admin(){
        return "admin/admin";
    }
}
