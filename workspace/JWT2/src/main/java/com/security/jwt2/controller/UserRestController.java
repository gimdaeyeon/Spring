package com.security.jwt2.controller;

import com.security.jwt2.domain.dto.user.UserDto;
import com.security.jwt2.exception.UserAlreadyExistsException;
import com.security.jwt2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users/*")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto){
        try {
            return ResponseEntity.ok(userService.register(userDto));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user){

        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}








