package com.security.jwt2.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponseDto {
    private int errorCode;
    private String message;
    private LocalDateTime eventTime;

    public ErrorResponseDto(int errorCode, String message,LocalDateTime eventTime) {
        this.errorCode = errorCode;
        this.eventTime = eventTime;
        this.message = message;
    }
}
