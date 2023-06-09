package com.example.test.mapper;

import com.example.test.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public Long selectUserNumber(@Param("userId") String userId, @Param("userPassword") String userPassword);

    public void insert(UserDTO userDto);

}
