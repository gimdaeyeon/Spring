package com.security.jwt2.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JwtTokenManagerTest {

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Test
    public void testSecretKeysInjection() {
//        assertThat(jwtTokenManager.getAccessKey()).isNotNull();
//        assertThat(jwtTokenManager.getRefreshKey()).isNotNull();
//
//        assertThat(jwtTokenManager.getAccessKey()).isNotEqualTo(jwtTokenManager.getRefreshKey());
    }

}