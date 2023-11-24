package com.example.securingweb.security;

import com.example.securingweb.dto.MemberDetails;
import com.example.securingweb.dto.UserDto;
import com.example.securingweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userMapper.selectByLoginId(username);

        if(userDto==null){
            throw new UsernameNotFoundException("해당 아이디가 존재하지 않습니다.");
        }
        return new MemberDetails(userDto);
    }
}
