package com.example.ex06.controller;

import com.example.ex06.dto.UserDto;
import com.example.ex06.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/filterTest")
    public void filterTest(){
        System.out.println("==========핸들러가 실행됐다.==========");
    }

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/join")
    public RedirectView join(UserDto userDto, Model model){
        userService.register(userDto);
        return new RedirectView("/user/login");
    }

    @PostMapping("/login")
    public RedirectView login( String userId, String userPassword, HttpServletRequest req){
        try {
            Long userNumber = userService.findUserNumber(userId,userPassword);
            req.getSession().setAttribute("userNumber",userNumber);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new RedirectView("/user/login");
        }
        return new RedirectView("/board/community");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate(); //세션 초기화
        return "user/login";
    }


}
