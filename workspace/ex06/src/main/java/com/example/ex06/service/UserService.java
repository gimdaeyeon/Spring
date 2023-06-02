package com.example.ex06.service;

import com.example.ex06.dto.UserDto;
import com.example.ex06.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;

//    회원가입
    public void register(UserDto userDto){
        if(userDto==null){
            throw new IllegalArgumentException("회원가입 정보 누락");
        }
        userMapper.insert(userDto);
    }

//    로그인
    public Long findUserNumber(String userId, String userPassword){
        if(userId==null||userPassword==null){
            throw new IllegalArgumentException("아이디 또는 패스워드 누락");
        }
        return Optional.ofNullable(userMapper.selectUserNumber(userId,userPassword))
                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 회원");});
    }



}
