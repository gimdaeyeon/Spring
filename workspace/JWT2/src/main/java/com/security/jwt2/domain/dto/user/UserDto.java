package com.security.jwt2.domain.dto.user;

import com.security.jwt2.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserDto(LocalDateTime createdDate, Long id, String loginId, LocalDateTime modifiedDate, String nickname, String password) {
        this.createdDate = createdDate;
        this.id = id;
        this.loginId = loginId;
        this.modifiedDate = modifiedDate;
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
