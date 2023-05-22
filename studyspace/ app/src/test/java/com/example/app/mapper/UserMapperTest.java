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
@Slf4j
@Transactional
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;

    @BeforeEach
    void setUp(){
        userDto = new UserDto();
        userDto.setUserId("bbb");
        userDto.setUserPassword("1234");
        userDto.setUserGender("F");
        userDto.setUserEmail("qwer@test.com");
        userDto.setUserAddress("뚝섬");
    }
    @Test
    @DisplayName("회원 가입및 해당 회원번호 조회")
    void insertAndSelectUserNumberByIdPassword() {
        userMapper.insert(userDto);

        assertThat(userDto.getUserNumber())
                .isEqualTo(userMapper.selectUserNumber(userDto.getUserId(), userDto.getUserPassword()));
    }
}