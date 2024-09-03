package com.security.jwt2.domain.document;

import com.security.jwt2.domain.base.Period;
import com.security.jwt2.domain.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("vue_user")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Period {
    @Id
    private String id;
    private String loginId;
    private String password;
    private String nickname;

    @Builder
    public User(String id, String loginId, String nickname, String password) {
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
