package com.security.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String home(HttpServletRequest request){
//        System.out.println(request.getHeader("accessToken"));
        return "main/home";
    }
    @GetMapping("/main/hello")
    public String hello(){
        return "main/hello";
    }
    @GetMapping("/admin/admin")
    public String admin(){
        return "admin/admin";
    }
}
