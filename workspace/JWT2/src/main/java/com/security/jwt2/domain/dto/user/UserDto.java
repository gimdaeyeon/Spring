package com.security.jwt2.domain.dto.user;

import com.security.jwt2.domain.document.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String id;
    private String loginId;
    private String password;
    private String nickname;

    public UserDto(String id, String loginId, String nickname, String password) {
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
    }

    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .loginId(loginId)
                .password(password)
                .build();
    }

}
