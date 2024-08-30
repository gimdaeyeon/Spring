package com.security.jwt2.controller;

import com.security.jwt2.domain.dto.user.UserDto;
import com.security.jwt2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/*")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/signup")
    public void join(@RequestBody UserDto userDto){
        userService.register(userDto);
    }

    @PostMapping("/login")
    public String login(){
        return "login";
    }

}








