package com.example.app.mapper;

import com.example.app.dto.BoardDto;
import com.example.app.dto.UserDto;
import com.example.app.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
@Transactional
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;
    private BoardDto boardDto;
    private BoardVo boardVo;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("ccc");
        userDto.setUserPassword("1234");
        userDto.setUserGender("M");
        userDto.setUserEmail("asdf@qewr");
        userDto.setUserAddress("신도림");
        userMapper.insert(userDto);

        boardDto= new BoardDto();
        boardDto.setBoardTitle("testTitle");
        boardDto.setBoardContent("testContent");
        boardDto.setUserNumber(userDto.getUserNumber());

        boardVo= new BoardVo();
        boardVo.setBoardTitle("testTitle");
        boardVo.setBoardContent("testContent");
        boardVo.setUserNumber(userDto.getUserNumber());
        boardVo.setUserId(userDto.getUserId());

        boardMapper.insert(boardDto);
    }

    @Test
    @DisplayName("게시물 등록")
    void insert() {
        assertThat(boardDto.getBoardNumber()).isNull();
        boardMapper.insert(boardDto);
        assertThat(boardDto.getBoardNumber()).isNotNull();
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        boardMapper.delete(boardDto.getBoardNumber());

        assertThat(boardMapper.select(boardDto.getBoardNumber())).isNull();
    }

    @Test
    void update() {
        boardDto.setBoardTitle("updateTitle");
        boardDto.setBoardContent("updateContent");

        boardMapper.update(boardDto);

        assertThat(boardDto.getBoardTitle())
                .isEqualTo(boardMapper.select(boardDto.getBoardNumber()).getBoardTitle());
    }

    @Test
    void selectAll() {
        assertThat(boardMapper.selectAll().size())
                .isGreaterThanOrEqualTo(1);
    }
}