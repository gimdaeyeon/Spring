package com.example.securingweb.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDto{
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private int age;

}
