package com.example.securingweb.service;

import com.example.securingweb.domain.AuthorityDto;
import com.example.securingweb.domain.UserDto;
import com.example.securingweb.domain.type.Authority;
import com.example.securingweb.mapper.AuthorityMapper;
import com.example.securingweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityMapper authorityMapper;

    public void register(UserDto userDto){
        if(userDto == null){
            throw new IllegalArgumentException("회원정보 누락");
        }
        passwordEncryption(userDto);
        userMapper.insert(userDto);
        registerDefaultUserAuthority(userDto);
    }

    private void passwordEncryption(UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }

    public UserDto findUserById(Long id){
        if(id == null){
            throw new IllegalArgumentException("회원id 누락");
        }
        return Optional.ofNullable(userMapper.selectById(id))
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원"));
    };

    public UserDto findUserByLoginId(String loginId){
        if(loginId == null){
            throw new IllegalArgumentException("회원 로그인id 누락");
        }
        return Optional.ofNullable(userMapper.selectByLoginId(loginId))
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원"));
    };

    private void registerDefaultUserAuthority(UserDto userDto){
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setUserId(userDto.getId());
        authorityDto.setAuthorityName(Authority.USER.name());
        authorityMapper.insert(authorityDto);
    }

    public List<AuthorityDto> findAuthorityById(Long id){
        if(id == null){
            throw new IllegalArgumentException("회원id 누락");
        }
        return authorityMapper.selectByUserId(id);
    }

}
