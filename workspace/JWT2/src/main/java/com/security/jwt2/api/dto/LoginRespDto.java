package com.security.jwt2.api.dto;

import com.security.jwt2.domain.dto.user.UserDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRespDto {
    private boolean success;
    private User user;
    private String message;
    private String token;

    public LoginRespDto(UserDto userDto) {
        this.user = new User(userDto);
    }

    @Builder
    public LoginRespDto(String message, boolean success, String token, User user) {
        this.message = message;
        this.success = success;
        this.token = token;
        this.user = user;
    }

    @Data
    @NoArgsConstructor
    public static class User {
        private String loginId;
        private String nickname;

        public User(UserDto dto) {
            this.loginId = dto.getLoginId();
            this.nickname = dto.getNickname();
        }
    }
}
