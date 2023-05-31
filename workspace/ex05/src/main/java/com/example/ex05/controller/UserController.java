package com.example.ex05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class UserController {

    @GetMapping("/filterTest")
    public void filterTest(){
        System.out.println("==========핸들러가 실행됐다.==========");
    }
}
