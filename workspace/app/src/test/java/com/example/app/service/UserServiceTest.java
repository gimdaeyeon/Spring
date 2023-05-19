package com.example.app.service;

import com.example.app.dto.UserDto;
import com.example.app.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceTest {
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;
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
    @DisplayName("회원등록")
    void register() {
        doNothing().when(userMapper).insert(any(UserDto.class));

        userService.register(userDto);

        verify(userMapper, times(1)).insert(userDto);
    }

    @Test
    @DisplayName("id,pw로 회원번호 조회")
    void findUserNumber() {
        doReturn(2L).when(userMapper).selectUserNumber(any(String.class),any(String.class));

        assertThat(2L)
                .isEqualTo(userService.findUserNumber("aaa","1234"));

    }
    @Test
    @DisplayName("id,pw로 회원번호 조회 : 예외검사")
    void findUserNumber2() {
        doReturn(null).when(userMapper).selectUserNumber(any(String.class),any(String.class));

        assertThatThrownBy(()->userService.findUserNumber("dfkls","321"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지");



    }
}