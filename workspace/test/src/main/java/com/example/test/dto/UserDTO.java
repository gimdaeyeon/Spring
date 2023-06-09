package com.example.test.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private Long userNumber;
    private String userId;
    private String userPassword;
    private String userName;

}
