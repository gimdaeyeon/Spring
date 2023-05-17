package com.example.ex04.service;

import com.example.ex04.dto.UserDto;
import com.example.ex04.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
class UserServiceTest {
    @Mock //가짜를 만들고
    private UserMapper userMapper;

    @InjectMocks //가짜객체를 주입하겠다
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserName("김철수");
        userDto.setUserAddress("강북구");
        userDto.setUserAge(22);
    }

    @Test
    @DisplayName("이름으로 회원정보 조회")
    void findUser() {
//        stubbing과정 ->  어떤 값을 넣을때 무엇을 반환할지 재정의 한다
        when(userMapper.select(any(String.class))).thenReturn(userDto);

//        when(userMapper.select(null)).thenThrow(IllegalArgumentException.class);
        when(userMapper.select(null)).thenReturn(null);

        assertThat(userService.findUser("dfdsfew").getUserName())
                .isEqualTo(userDto.getUserName());

        assertThatThrownBy(() -> userService.findUser(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재"); //메세지의 일부가 일치하면 true
    }

    @Test
    @DisplayName("전체 회원 조회")
    void findAll() {
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        List<String> list2 = List.of("a", "b","c"); //간편하게 리스트를 만드는 방법

        when(userMapper.selectAll()).thenReturn(List.of(userDto));
        assertThat(userService.findAll().get(0).getUserName())
                .isEqualTo(userDto.getUserName());
    }

    @Test
    void register() {
//        when(userMapper.insert(any(String.class))).thenThrow(); 반환타입이 void인 메소드는 stubbing불가능
//        반환타입이 vodi인 경우 다음과 같이 스터빙한다.
        doNothing().when(userMapper).insert(any(UserDto.class));
//        doReturn(List.of(userDto)).when(userMapper).selectAll();
//        doThrow(IllegalArgumentException.class).when(userMapper).select(null);
        userService.register(userDto);

        // 어떤 메소드가 몇 번 실행되었는지 확인
        verify(userMapper, times(1)).insert(any(UserDto.class));
    }

    @Test
    @DisplayName("회원 삭제")
    void remove() {
//        given
        doNothing().when(userMapper).delete(any(Long.class));
//        doThrow(IllegalArgumentException.class).when(userMapper).delete();

//        when
        userService.remove(1L);

//        then
        verify(userMapper, times(1)).delete(any(Long.class));

    }


//    @Test
//    @DisplayName("이름으로 회원 전체정보 조회하기🌟")
//    void findUser() {
//        userService.register(userDto); //조회를 위해 먼저 회원등록
//        assertThat(userDto.getUserAddress())
//                .isEqualTo(userService.findUser("김철수").getUserAddress());
//    }
//    @Test
//    @DisplayName("이름으로 해당 회원정보 조회하기🌟 예외 확인")
//    void findUserException() {
//        assertThatThrownBy(()->{
//            userService.findUser("강백호");
//        }).isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("존재하지 않는 회원입니다.");
//    }
//
//    @Test
//    @DisplayName("등록된 회원 전체 조회하기")
////    @Transactional(readOnly = true)
//    void findAll() {
//        userService.register(userDto);
//        userService.register(userDto);
//        userService.register(userDto);
//
//        List<UserDto> userList = userService.findAll();
//    }
//
//    @Test
//    @DisplayName("회원가입")
//    void register() {
////        assertThat(userDto.getUserAddress())
////                .isEqualTo(userService.findUser("김철수").getUserAddress());
//        userService.register(userDto);
//
//    }
//


}