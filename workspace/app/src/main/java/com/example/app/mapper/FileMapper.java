package com.example.app.mapper;

import com.example.app.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
//    사진 등록
    public void insert(FileDto fileDto);
//    사진 삭제
    public void delete(Long boardNumber);
//    게시물 번호로 사진 조회
    public List<FileDto> selectList(Long boardNumber);

}
