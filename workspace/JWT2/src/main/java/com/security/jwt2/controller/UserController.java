package com.security.jwt2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/login")
    public String login(HttpServletRequest req,HttpServletResponse resp){
        return "user/login";
    }
    @GetMapping("/join")
    public String join(HttpServletRequest req){
        return "user/join";
    }






}








