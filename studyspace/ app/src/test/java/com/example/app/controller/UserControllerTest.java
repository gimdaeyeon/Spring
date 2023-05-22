package com.example.app.controller;

import com.example.app.dto.UserDto;
import com.example.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private UserDto userDto;


    @Test
    @DisplayName("회원가입 처리")
    void join() throws Exception{
        doNothing().when(userService).register(any(UserDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/join")
                .param("userId", "aaa")
                .param("userPassword", "1234")
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    @DisplayName("join페이지 이동")
    void testJoin() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/join"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그인 처리")
    void login() throws Exception{
        doReturn(3L).when(userService).findUserNumber(any(String.class),any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
            .param("userId","aaa")
            .param("userPassword","1234"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그인 페이지 이동")
    void testLogin()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그아웃 처리")
    void logout() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/logout")
                .sessionAttr("userNumber", 3L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그인 처리: 존재하지 않는 회원 -> 예외처리")
    void loginException() throws Exception{
        doThrow(IllegalArgumentException.class).when(userService)
                .findUserNumber(any(String.class),any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("userId", "aaa")
                .param("userPassword", "1234"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/login"))
        .andDo(MockMvcResultHandlers.print());
    }






}