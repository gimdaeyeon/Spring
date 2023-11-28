package com.example.securingweb.controller;

import com.example.securingweb.domain.UserDto;
import com.example.securingweb.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class MvcController {

    private final UserService userService;


    @RequestMapping({"/","/home"})
    public String goHome(){
        return "home";
    }

    @GetMapping("/login")
    public String goLogin(){
        return "login";
    }

    @GetMapping("/hello")
    public String goHelloPage(HttpServletRequest req){
        System.out.println(req.getHeader("Cookie"));
        return "hello";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }
    @PostMapping("/join")
    public RedirectView joinOk(UserDto userDto){
        userService.register(userDto);
        return new RedirectView("/login");
    }

    @GetMapping("/admin")
    public String goAdmin(){
        return "admin";
    }



}
