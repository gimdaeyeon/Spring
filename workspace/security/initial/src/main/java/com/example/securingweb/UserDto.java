package com.example.securingweb;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Setter @Getter
@ToString
public class UserDto{
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private int age;

}
