package com.example.app.mapper;

import com.example.app.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class FileMapperTest {

    @Autowired
    private FileMapper fileMapper;
    private FileDto fileDto;

    @BeforeEach
    void setUp() {
        fileDto = new FileDto();
        fileDto.setFileUuid("testUuid");
        fileDto.setBoardNumber(90L);
        fileDto.setFileName("testName");
        fileDto.setFileUploadPath("testUploadPath");

    }

    @Test
    @DisplayName("파일등록 및 조회")
    void insertAndSelectListTest() {
        List<FileDto> file = fileMapper.selectList(90L);
        fileMapper.insert(fileDto);

        List<FileDto> foundFile = fileMapper.selectList(90L);

        assertThat(file.size()+1).isEqualTo(foundFile.size());
    }

    @Test
    @DisplayName("파일삭제 테스트")
    void delete() {
        fileMapper.insert(fileDto);
        List<FileDto> file = fileMapper.selectList(90L);

        fileMapper.delete(fileDto.getBoardNumber());
        List<FileDto> foundFile = fileMapper.selectList(90L);

        assertThat(file.size()).isNotEqualTo(foundFile.size());
    }

}