package com.example.app.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Data
public class FileDto {
    private Long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long boardNumber;
}
