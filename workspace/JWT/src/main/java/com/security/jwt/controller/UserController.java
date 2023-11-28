package com.security.jwt.controller;

import com.security.jwt.domain.entity.User;
import com.security.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public void login(){}
    @GetMapping("/join")
    public void join(){}

    @PostMapping("/join")
    public RedirectView join(User user){
        userService.register(user);
        return new RedirectView("/");
    }

}
