package com.security.jwt2.domain.dto.user;

import com.security.jwt2.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;

    public UserDto(Long id, String loginId, String nickname, String password) {
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
    }

    public User toEntity(){
        return User.builder()
                .id(id)
                .nickname(nickname)
                .loginId(loginId)
                .password(password)
                .build();
    }

}
