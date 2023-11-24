package com.example.securingweb.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Data
public class UserDto{
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private int age;

}
