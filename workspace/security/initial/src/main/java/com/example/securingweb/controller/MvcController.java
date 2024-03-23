package com.example.securingweb.controller;

import com.example.securingweb.domain.MemberDetails;
import com.example.securingweb.domain.UserDto;
import com.example.securingweb.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;


@Controller
@RequiredArgsConstructor
public class MvcController {

    private final UserService userService;


    @RequestMapping({"/","/home"})
    public String goHome(HttpSession session, HttpServletRequest request, HttpServletResponse response){
//        HttpSession session = request.getSession();


        return "home";
    }

    @GetMapping("/login")
    public String goLogin(){
        return "login";
    }

    @GetMapping("/hello")
    public String goHelloPage(HttpServletRequest req){

        Cookie[] cookies = req.getCookies();
        Cookie c = null;
        for(Cookie cookie :cookies){
            if(cookie.getName().equals("cookieName")){
                c = cookie;
                break;
            }
        }

        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SecurityContext securityContext = (SecurityContext)req.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        securityContext.getAuthentication().getAuthorities();

//        req.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");

        MemberDetails memberDetails2 = (MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication = " + authentication);

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
