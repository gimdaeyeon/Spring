package com.example.ex04.service;

import com.example.ex04.dto.UserDto;
import com.example.ex04.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional //각 메소드별로 트렌젝션 실행
public class UserService {
    private final UserMapper userMapper;


//    이름으로 회원조회
    @Transactional(readOnly = true) // read만 하는 메소드(select)에다가 붙여주면 성능이 향상된다.
    public UserDto findUser(String userName){
        if(userName==null){
            throw new IllegalArgumentException("유저 이름 누락");
        }

        return Optional.ofNullable(userMapper.select(userName))
                .orElseThrow(()->{
                    throw new IllegalArgumentException("존재하지 않는 회원의 이름입니다.");
                });
    }
//    회원 전체 조회
    public List<UserDto> findAll(){
        return userMapper.selectAll();
    }

//    회원가입
    public void register(UserDto userDto){
        if(userDto==null){
            throw new IllegalArgumentException("회원정보 누락");
        }
        userMapper.insert(userDto);
    }

//    회원삭제
    public void remove(Long userNumber){
        if(userNumber==null){
            throw new IllegalArgumentException("회원 번호 누락");
        }

        userMapper.delete(userNumber);
    }




}
