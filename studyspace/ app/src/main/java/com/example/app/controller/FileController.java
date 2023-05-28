package com.example.app.controller;

import com.example.app.dto.FileDto;
import com.example.app.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/*")
public class FileController {
    private final FileService fileService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/imgList")
    public List<FileDto> imgList(Long boardNumber){
        return fileService.findList(boardNumber);
    }

//    서버 컴퓨터에 저장된 파일을 복사하여 넘겨주는 핸들러
    @GetMapping("/display")
    public byte[] display(String fileName)throws IOException {
        return FileCopyUtils.copyToByteArray(new File(fileDir,fileName));
    }

//    사진 다운로드 처리
    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileName)throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource(fileDir+fileName);
        HttpHeaders headers = new HttpHeaders();
        String name = resource.getFilename();

        name=name.substring(name.indexOf("_")+1);

        headers.add("Content-Disposition","attachment;filename="+ URLEncoder.encode(name,"UTF-8"));
        return new ResponseEntity<>(resource,headers, HttpStatus.OK);
    }




}
