package com.example.app.service;

import com.example.app.dto.FileDto;
import com.example.app.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {
    private final FileMapper fileMapper;

    @Value("${file.dir}")
    private String fileDir;

//    사진 등록
    public void register(FileDto fileDto){
        if(fileDto==null){
            throw new IllegalArgumentException("파일 정보 누락");
        }
        fileMapper.insert(fileDto);
    }

//    사진 삭제
    public void remove(Long boardNumber){
        if(boardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락(file)");
        }
        fileMapper.delete(boardNumber);
    }

//    게시글 번호로 사진 조회
    public List<FileDto> findList(Long boardNumber){
        return fileMapper.selectList(boardNumber);
    }

//    파일 저장 처리
    public FileDto saveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자 포함)
            String originName = new String(file.getOriginalFilename().getBytes(), "UTF-8");

//        파일 이름에 붙여줄 uuid생성(파일이름 중복이 나오지 않게 처리
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString()+"_"+originName;

        File uploadPath = new File(fileDir,getUploadPath());

//        경로가 존재하지 않는다면(폴더가 없으면)
        if(uploadPath.exists()){
//         경로에 필요한 폴더를 생성한다.
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath,sysName);


      return null;
    }


//      파일이 저장되는 하위 경로를 현재 날짜로 설정할 것이기 떄문에 현재 날짜를 구한다.
    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }




}







