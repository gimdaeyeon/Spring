package com.example.ex05.controller;

import com.example.ex05.dto.UserDto;
import com.example.ex05.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Slf4j
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName("회원가입 처리")
    void join() throws Exception {
        doNothing().when(userService).register(any(UserDto.class));

        mockMvc.perform(post("/user/join")
                .param("userId", "aaa")
                .param("userPassword", "1234")
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())   //검증
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/login"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원가입 페이지 이동")
    void testJoin() throws Exception {
        mockMvc.perform(get("/user/join"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/join"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("login페이지 이동")
    void login() throws Exception {

        mockMvc.perform(get("/user/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/login"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그인 처리")
    void testLogin() throws Exception {
        doReturn(1L).when(userService).findUserNumber(any(String.class), any(String.class));

        mockMvc.perform(post("/user/login")
                .param("userId", "aaa")
                .param("userPassword", "1234")
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/board/community"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그아웃 처리")
    void logout() throws Exception {
        mockMvc.perform(get("/user/logout")
                .sessionAttr("userNumber", 1L))     //세션에 userNumber==1을 담아서 보낸다.
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("존재하지 않는 회원인 경우")
    void testLogin2() throws Exception {
        doThrow(IllegalArgumentException.class).when(userService).findUserNumber(any(String.class), any(String.class));

        mockMvc.perform(post("/user/join")
                .param("userId", "aaa")
                .param("userPassword", "1234")
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/login"))
                .andDo(MockMvcResultHandlers.print());
    }
}