package com.security.jwt2.domain.entity;

import com.security.jwt2.domain.base.Period;
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
    private String name;
    private Integer age;

    @Builder
    public User(Integer age, Long id, String loginId, String name, String password) {
        this.age = age;
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
