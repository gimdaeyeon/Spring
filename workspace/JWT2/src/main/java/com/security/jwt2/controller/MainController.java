package com.security.jwt2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String home(HttpServletResponse resp){

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
