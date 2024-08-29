package com.security.jwt2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserRestController {

    @GetMapping("/test")
    public String login(){
        return "hello";
    }






}








