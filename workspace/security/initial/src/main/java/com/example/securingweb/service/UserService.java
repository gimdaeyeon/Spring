package com.example.securingweb.service;

import com.example.securingweb.dto.UserDto;
import com.example.securingweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void register(UserDto userDto){
        if(userDto == null){
            throw new IllegalArgumentException("회원정보 누락");
        }
        passwordEncryption(userDto);
        userMapper.insert(userDto);
    }

    private void passwordEncryption(UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }


}
