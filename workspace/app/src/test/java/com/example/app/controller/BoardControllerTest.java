package com.example.app.controller;

import com.example.app.service.BoardService;
import com.example.app.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BoardController.class)
@Slf4j
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;    // 순수하게 목 객체를 만든다

    @MockBean //만들고 빈등록까지
    private BoardService boardService;
    private BoardVo boardVo;

    @BeforeEach
    void setUp(){
        boardVo = new BoardVo();
        boardVo.setBoardTitle("testTitle");
        boardVo.setBoardContent("testContent");
        boardVo.setUserId("aaa");
        boardVo.setUserNumber(1L);
    }

    @Test
    void showBoardList() throws Exception{
//        doReturn(List.of(boardVo)).when(boardService).findAll();
//
//        mockMvc.perform(get("/board/list"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(boardService, times(1)).findAll();
    }

    @Test
    void boardWrite() throws Exception{
        mockMvc.perform(get("/board/write")
                .sessionAttr("userNumber",1l)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void boardView() {
    }
}