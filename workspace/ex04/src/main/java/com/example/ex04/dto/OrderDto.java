package com.example.ex04.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
public class OrderDto {
    private Long userNumber;
    private Long productNumber;
    private int orderCnt;
}
