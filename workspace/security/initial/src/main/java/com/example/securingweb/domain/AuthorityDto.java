package com.example.securingweb.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthorityDto {
        private Long id;  // PK
        private Long userId;
        private String authorityName;  // 권한명 ex) "ROLE_MEMBER", "ROLE_ADMIN"
}
