package com.example.test.service;

import com.example.test.dto.UserDTO;
import com.example.test.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void registerUser(UserDTO userDTO){

        userMapper.insert(userDTO);

    }

    public Long findUserNumber(String userId, String userPassword){
        return userMapper.selectUserNumber(userId, userPassword);
    }
}