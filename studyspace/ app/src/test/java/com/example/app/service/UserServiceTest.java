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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("bbb");
        userDto.setUserPassword("12343454");
        userDto.setUserGender("F");
        userDto.setUserEmail("test@qwer");
        userDto.setUserAddress("마포구");
    }

    @Test
    @DisplayName("회원등록")
    void register() {
        doNothing().when(userMapper).insert(any(UserDto.class));

        userService.register(userDto);

        verify(userMapper, times(1)).insert(userDto);
    }

    @Test
    @DisplayName("아이디, 비밀번호로 회원번호 조회")
    void findUserNumber() {
        doReturn(21L).when(userMapper).selectUserNumber(any(String.class),any(String.class));

        assertThat(21L).isEqualTo(userService.findUserNumber("id","password"));
    }

    @Test
    @DisplayName("아이디, 비밀번호로 회원번호 조회 : 예외검사")
    void findUserNumberException() {
        doReturn(null).when(userMapper).selectUserNumber(any(String.class),any(String.class));

        assertThatThrownBy(()->userService.findUserNumber("id","password"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지");
    }
}