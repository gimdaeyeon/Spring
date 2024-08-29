package com.security.jwt2.domain.entity;

import com.security.jwt2.domain.base.Period;
import com.security.jwt2.domain.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_USER")
@Getter @Setter @ToString
@SequenceGenerator(
        name = "SEQ_USER_GENERATOR",
        sequenceName = "SEQ_USER",
        allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Period {
    @Id @GeneratedValue(generator ="SEQ_USER_GENERATOR" )
    private Long id;
    private String loginId;
    private String password;
    private String nickname;

    @Builder
    public User(Long id, String loginId, String nickname, String password) {
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
    }

    public UserDto toDto(){
        UserDto user = new UserDto();
        user.setId(id);
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setNickname(nickname);
        return user;
    }

}
