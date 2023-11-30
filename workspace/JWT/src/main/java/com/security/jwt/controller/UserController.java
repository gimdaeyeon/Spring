package com.security.jwt.controller;

import com.security.jwt.domain.entity.User;
import com.security.jwt.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @PostMapping("/login")
    public RedirectView login(User user, HttpServletResponse resp){
        String accessToken = null;
        try {
            accessToken = userService.authenticateAndGetJwt(user.getLoginId(),user.getPassword());
        } catch (UsernameNotFoundException e) {
//            e.printStackTrace();
            return new RedirectView("/user/login?fail");
        }
        Cookie cookie = new Cookie("accessToken",accessToken);
        cookie.setPath("/");
//        cookie.setHttpOnly(true);

        resp.addCookie(cookie);


        return new RedirectView("/");
    }


}
