package com.example.app.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Url {
    public static final String BOARD_URL = "/board/*";
    public static final String USER_URL = "/user/*";
}
