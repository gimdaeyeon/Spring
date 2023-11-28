package com.security.jwt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class User {
    @Id @GeneratedValue(generator ="SEQ_USER_GENERATOR" )
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    List<UserAuthority> authorities = new ArrayList<>();
}
