package com.example.app.mapper;

import com.example.app.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
//    회원 등록
    void insert(UserDto userDto);
//    아이디 비밀번호로 회원번호 가져오기(로그인)
    Long selectUserNumber(@Param("userId")String userId,@Param("userPassword")String userPassword);
}
