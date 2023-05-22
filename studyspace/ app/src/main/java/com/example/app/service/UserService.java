package com.example.app.service;

import com.example.app.dto.UserDto;
import com.example.app.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

//    회원등록
    public void register(UserDto userDto){
        if(userDto==null){
            throw new IllegalArgumentException("회원정보 누락");
        }
        userMapper.insert(userDto);
    }

    /**
     *
     * @param userId
     * @param userPassword
     * @return
     * @throws IllegalArgumentException 존재하지 않는 회원 id, pw로 조회하는 경우, 일치하지 않는 경우
     */
    public Long findUserNumber(String userId, String userPassword){
        if(userId==null||userPassword==null){
            throw new IllegalArgumentException("아이디 또는 비밀번호 누락");
        }
        return Optional.ofNullable(userMapper.selectUserNumber(userId,userPassword))
                .orElseThrow(()->{
                  throw new IllegalArgumentException("존재하지 않는 회원입니다.");
                });
    }



}
