package com.security.jwt.domain.entity;

import com.security.jwt.domain.enumType.Authority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBL_AUTHORITY")
@Getter
@Setter
@ToString
@SequenceGenerator(
        name = "SEQ_AUTHORITY_GENERATOR",
        sequenceName = "SEQ_AUTHORITY",
        allocationSize = 1
)
public class UserAuthority {
    @Id @GeneratedValue(generator = "SEQ_AUTHORITY_GENERATOR")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORITY_NAME")
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
