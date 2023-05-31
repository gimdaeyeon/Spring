package com.example.ex05.mapper;

import com.example.ex05.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("uuu");
        userDto.setUserPassword("4321");
        userDto.setUserName("김철수");
    }

    @Test
    @DisplayName("회원가입 및 로그인")
    void insertAndGetNumber() {
        userMapper.insert(userDto);

        assertThat(userDto.getUserNumber())
                .isEqualTo(userMapper.selectUserNumber(userDto.getUserId(),userDto.getUserPassword()));

    }


}