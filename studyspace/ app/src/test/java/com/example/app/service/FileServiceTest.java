package com.example.app.service;

import com.example.app.dto.FileDto;
import com.example.app.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class FileServiceTest {
    @Mock
    private FileMapper fileMapper;
    @InjectMocks
    private FileService fileService;
    private FileDto fileDto;
    private MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        fileDto = new FileDto();
        fileDto.setFileUuid("testUuid");
        fileDto.setBoardNumber(90L);
        fileDto.setFileName("testName");
        fileDto.setFileUploadPath("testUploadPath");

        InputStream inputStream = mock(InputStream.class);
        file = new MockMultipartFile("mockName","mockOriginName","image",inputStream);
    }

    @Test
    @DisplayName("사진등록")
    void register() {
        doNothing().when(fileMapper).insert(fileDto);

        fileService.register(fileDto);

        verify(fileMapper,times(1)).insert(fileDto);
    }

    @Test
    @DisplayName("사진삭제")
    void remove() {
        doNothing().when(fileMapper).delete(any(Long.class));

        fileService.remove(fileDto.getBoardNumber());

        verify(fileMapper,times(1)).delete(fileDto.getBoardNumber());
    }

    @Test
    @DisplayName("사진가져오기")
    void findList() {
        doReturn(List.of(fileDto)).when(fileMapper).selectList(any(Long.class));

        assertThat(fileService.findList(3L).size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("실제 파일 저장")
    void saveFile() throws IOException{

        fileService.saveFile(file);

    }

    @Test
    void registerAndSaveFile() {
    }
}