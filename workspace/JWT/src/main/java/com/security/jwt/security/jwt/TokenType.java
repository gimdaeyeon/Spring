package com.security.jwt.security.jwt;

import lombok.Getter;
@Getter
public enum TokenType {
    ACCESS_TOKEN(30 * 60 * 1000L), // 유효기간 30분
    REFRESH_TOKEN(15 * 24 * 60 * 60 * 1000L) // 유효기간 15일
    ;

    private final long maxAge;
    TokenType(long maxAge) {
        this.maxAge = maxAge;
    }

}
