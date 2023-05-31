package com.example.ex05.mapper;

import com.example.ex05.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
//    회원가입
    void insert(UserDto userDto);
//    로그인
    Long selectUserNumber(@Param("userId")String userId, @Param("userPassword")String userPassword);


}
