package com.example.ex04.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
public class UserDto {
    private Long userNumber;
    private String userName;
    private String userAddress;
    private int userAge;
}
