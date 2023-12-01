package com.security.jwt.controller;

import com.security.jwt.domain.entity.User;
import com.security.jwt.security.jwt.JwtTokenProvider;
import com.security.jwt.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String login(HttpServletRequest req){
        String token = jwtTokenProvider.getTokenByRequest(req);
        token = jwtTokenProvider.validateToken(token)?token:null;

        if(token != null){
            return "main/hello";
        }


        return "user/login";
    }
    @GetMapping("/join")
    public String join(HttpServletRequest req){
        String token = jwtTokenProvider.getTokenByRequest(req);
        token = jwtTokenProvider.validateToken(token)?token:null;

        if(token != null){
            return "main/hello";
        }
        return "user/join";
    }

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
        cookie.setHttpOnly(true);

        resp.addCookie(cookie);

        return new RedirectView("/");
    }

    @PostMapping("/logout")
    public RedirectView logout(HttpServletResponse resp){
        Cookie accessCookie = new Cookie("accessToken",null);
        accessCookie.setMaxAge(0);
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);

        resp.addCookie(accessCookie);
        SecurityContextHolder.clearContext();

        return new RedirectView("/user/login");
    }

}
