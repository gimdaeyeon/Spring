package com.example.ex03.service;

import com.example.ex03.dto.BoardDto;
import com.example.ex03.mapper.BoardMapper;
import com.example.ex03.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
class BoardServiceTest {
    @Mock
    private BoardMapper boardMapper;

    @InjectMocks
    private BoardService boardService;
    private SearchVo searchVo;

    @BeforeEach
    void setUp(){
        searchVo = new SearchVo();
        searchVo.setKeyword("또로롱");
        searchVo.setSearchType("boardTitle");

    }

    @Test
    void searchBoard() {
    }
}