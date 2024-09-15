package com.security.jwt2.domain.entity;

import com.security.jwt2.domain.base.Period;
import com.security.jwt2.domain.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vue_user")
@Getter @Setter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        user.setCreatedDate(getCreatedDate());
        user.setModifiedDate(getModifiedDate());
        return user;
    }

}
