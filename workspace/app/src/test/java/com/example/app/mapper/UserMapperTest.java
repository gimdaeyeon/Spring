package com.example.app.mapper;

import com.example.app.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;

    @BeforeEach
    void setUp(){
        userDto = new UserDto();
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");
        userDto.setUserGender("M");
        userDto.setUserEmail("rlaeodus@naver.com");
        userDto.setUserAddress("우리집");
    }


    @Test
    @DisplayName("회원가입 및 조회 테스트")
    void insertAndSelectUserNumber() {
        userMapper.insert(userDto);

        Long foundUserNumber = userMapper.selectUserNumber(userDto.getUserId(),userDto.getUserPassword());

        assertThat(userDto.getUserNumber()).isEqualTo(foundUserNumber);

    }

}