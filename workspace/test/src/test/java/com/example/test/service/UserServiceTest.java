package com.example.test.service;

import com.example.test.dto.UserDTO;
import com.example.test.mapper.UserMapper;
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
    private UserDTO userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDTO();
        userDto.setUserNumber(1L);
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");
        userDto.setUserName("홍길동");
    }

    @Test
    @DisplayName("회원 등록")
    void registerUser() {
//        스터빙
        doNothing().when(userMapper).insert(any(UserDTO.class));
//        실행 상황
        userService.registerUser(userDto);
//         검증
        verify(userMapper, times(1)).insert(userDto);
    }

    @Test
    @DisplayName("회원번호 조회")
    void findUserNumber() {
//        스터빙
        doReturn(1L).when(userMapper).selectUserNumber(any(String.class),any(String.class));
//        실행 상황
        Long result = userService.findUserNumber("fdss","sdfdsf");
//         검증
        assertThat(userDto.getUserNumber()).isEqualTo(result);
    }
}