package com.example.securingweb.mapper;

import com.example.securingweb.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(UserDto userDto);
    UserDto selectById(Long id);
    UserDto selectByLoginId(String loginId);

}
