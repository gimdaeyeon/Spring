package com.example.ex05.service;

import com.example.ex05.dto.UserDto;
import com.example.ex05.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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
    void setUp() {
        userDto = new UserDto();
        userDto.setUserNumber(3L);
        userDto.setUserId("uuu");
        userDto.setUserPassword("4321");
        userDto.setUserName("김철수");
    }

    @Test
    @DisplayName("회원가입")
    void register() {
        doNothing().when(userMapper).insert(any(UserDto.class));

        userService.register(userDto);

        verify(userMapper,times(1)).insert(userDto);
    }

    @Test
    @DisplayName("로그인")
    void findUserNumber() {
        doReturn(2L).when(userMapper).selectUserNumber(any(String.class),any(String.class));

        assertThat(userService.findUserNumber("sfds","dfdsf")+1)
                .isEqualTo(userDto.getUserNumber());

    }
}