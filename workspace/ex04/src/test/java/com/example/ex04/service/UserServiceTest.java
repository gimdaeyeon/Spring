package com.example.ex04.service;

import com.example.ex04.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    void setUp(){
        userDto = new UserDto();
        userDto.setUserName("김철수");
        userDto.setUserAddress("강북구");
        userDto.setUserAge(22);
    }

    @Test
    @DisplayName("이름으로 회원 전체정보 조회하기🌟")
    void findUser() {
        userService.register(userDto); //조회를 위해 먼저 회원등록
        assertThat(userDto.getUserAddress())
                .isEqualTo(userService.findUser("김철수").getUserAddress());
    }
    @Test
    @DisplayName("이름으로 해당 회원정보 조회하기🌟 예외 확인")
    void findUserException() {
        assertThatThrownBy(()->{
            userService.findUser("강백호");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 회원입니다.");
    }

    @Test
    @DisplayName("등록된 회원 전체 조회하기")
//    @Transactional(readOnly = true)
    void findAll() {
        userService.register(userDto);
        userService.register(userDto);
        userService.register(userDto);

        List<UserDto> userList = userService.findAll();
    }

    @Test
    @DisplayName("회원가입")
    void register() {
//        assertThat(userDto.getUserAddress())
//                .isEqualTo(userService.findUser("김철수").getUserAddress());
        userService.register(userDto);

    }

    @Test
    void remove() {
    }
}