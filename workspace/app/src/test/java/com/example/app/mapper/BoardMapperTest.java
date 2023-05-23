package com.example.app.mapper;

import com.example.app.dto.BoardDto;
import com.example.app.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private UserMapper userMapper;
    private BoardDto boardDto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");
        userDto.setUserGender("M");
        userDto.setUserEmail("fdsf@dfdsf");
        userDto.setUserAddress("중구");
        userMapper.insert(userDto);

        boardDto = new BoardDto();

        boardDto.setBoardTitle("test Title");
        boardDto.setBoardContent("test content");
        boardDto.setUserNumber(userDto.getUserNumber());

        boardMapper.insert(boardDto);
    }

    @Test
    void delete() {
        boardMapper.delete(boardDto.getBoardNumber());
        assertThat(boardMapper.select(boardDto.getBoardNumber())).isNull();
    }

    @Test
    void update() {
        boardDto.setBoardTitle("update title");
        boardDto.setBoardContent("update content");

        boardMapper.update(boardDto);

        assertThat(boardMapper.select(boardDto.getBoardNumber()).getBoardTitle())
                .isEqualTo(boardDto.getBoardTitle());

    }

    @Test
    void selectAll() {
//        int beforeSize = boardMapper.selectAll().size();
//
//        boardMapper.insert(boardDto);
//
//        assertThat(boardMapper.selectAll().size()).isEqualTo(beforeSize+1);
    }
}