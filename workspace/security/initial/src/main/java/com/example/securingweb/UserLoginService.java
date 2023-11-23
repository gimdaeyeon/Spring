package com.example.securingweb;

import com.example.securingweb.dto.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
        System.out.println(username);

        MemberDetails user = null;

        if (username.equals("aaa")) {
//            BCrypt로 비밀번호 암호화

            String password = bCryptPasswordEncoder.encode("aaa");

            UserDto userDto = new UserDto();
            userDto.setId(1L);
            userDto.setLoginId("aaa");
            userDto.setPassword(password);
            userDto.setAge(15);
            userDto.setName("홍길동");
            user = new MemberDetails(userDto);
            System.out.println(userDto);
        }

        if (user == null) {
            System.out.println("인증 미완료");
            throw new UsernameNotFoundException("일치하는 회원이 아닙니다.");
        }
        return user;
    }
}
