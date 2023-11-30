package com.example.securingweb.controller;

import com.example.securingweb.domain.MemberDetails;
import com.example.securingweb.domain.UserDto;
import com.example.securingweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SecurityContext securityContext = (SecurityContext)req.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        securityContext.getAuthentication().getAuthorities();

//        req.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");

        MemberDetails memberDetails2 = (MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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
