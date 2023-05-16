package com.example.ex04.mapper;

import com.example.ex04.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
//    이름으로 회원조회
    UserDto select(String userName);
//    전체 회원조회
    List<UserDto> selectAll();
//    회원등록
    void insert(UserDto userDto);
//    호원삭제
    void delete(Long userNumber);


}
