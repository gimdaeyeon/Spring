package com.security.jwt2.api.controller;

import com.security.jwt2.api.dto.LoginRespDto;
import com.security.jwt2.domain.dto.user.UserDto;
import com.security.jwt2.exception.AlreadyExistsException;
import com.security.jwt2.security.jwt.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(userService.register(userDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginRespDto> login(@RequestBody UserDto user) {

        try {
            UserDto loggedUser = userService.login(user);
            String accessToken = jwtUtil.createAccessToken(loggedUser.getLoginId());
            LoginRespDto resp = new LoginRespDto(loggedUser);
            resp.setToken(accessToken);
            resp.setSuccess(true);
            resp.setMessage("Login Success");
            return ResponseEntity.ok(resp);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(LoginRespDto.builder()
                            .success(false)
                            .message("Authentication failed. User not found")
                            .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(LoginRespDto.builder()
                            .success(false)
                            .message("Internal Server Error")
                            .build());
        }
    }

}








