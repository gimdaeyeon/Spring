package com.example.securingweb.security;

import com.example.securingweb.domain.MemberDetails;
import com.example.securingweb.domain.UserDto;
import com.example.securingweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.findUserByLoginId(username);

        if(userDto==null){
            throw new UsernameNotFoundException("해당 아이디가 존재하지 않습니다.");
        }
        MemberDetails memberDetails = new MemberDetails(userDto);
        memberDetails.setUserService(userService);

        return memberDetails;
//        return new User(username,userDto.getPassword(),memberDetails.getAuthorities());
    }
}
