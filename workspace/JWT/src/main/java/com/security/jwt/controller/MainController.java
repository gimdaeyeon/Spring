package com.security.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String home(){
        return "main/home";
    }
    @GetMapping("/main/hello")
    public void hello(){}
    @GetMapping("/admin/admin")
    public void admin(){}
}
