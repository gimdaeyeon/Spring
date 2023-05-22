package com.example.app.service;

import com.example.app.dto.BoardDto;
import com.example.app.mapper.BoardMapper;
import com.example.app.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @Mock
    private BoardMapper boardMapper;

    @InjectMocks
    private BoardService boardService;
    private BoardDto boardDto;
    private BoardVo boardVo;

    @BeforeEach
    void setUp() {
        boardDto= new BoardDto();
        boardDto.setBoardTitle("testTitle");
        boardDto.setBoardContent("testContent");
        boardDto.setUserNumber(3L);
        boardDto.setBoardNumber(1L);

        boardVo= new BoardVo();
        boardVo.setBoardTitle("testTitle");
        boardVo.setBoardContent("testContent");
        boardVo.setUserNumber(3L);
        boardVo.setUserId("ooo");
        boardVo.setBoardNumber(1L);
    }

    @Test
    @DisplayName("게시물 등록")
    void register() {
        doNothing().when(boardMapper).insert(any(BoardDto.class));

        boardService.register(boardDto);

        verify(boardMapper, times(1)).insert(boardDto);
    }

    @Test
    @DisplayName("게시물 삭제")
    void remove() {
        doNothing().when(boardMapper).delete(any(Long.class));

        boardService.remove(2L);

        verify(boardMapper, times(1)).delete(2L);
    }

    @Test
    @DisplayName("게시물 수정")
    void modify() {
        doNothing().when(boardMapper).update(any(BoardDto.class));

        boardService.modify(boardDto);

        verify(boardMapper, times(1)).update(boardDto);
    }

    @Test
    @DisplayName("게시물 조회")
    void findBoard() {
        doReturn(boardVo).when(boardMapper).select(any(Long.class));

        assertThat(boardVo).isEqualTo(boardService.findBoard(4L));
    }

    @Test
    @DisplayName("게시물 전체 조회")
    void findAll() {
        doReturn(List.of(boardVo)).when(boardMapper).selectAll();

        assertThat(boardService.findAll().size()).isGreaterThanOrEqualTo(1);
    }
}
