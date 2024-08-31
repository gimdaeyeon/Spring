package com.security.jwt2.security;

import com.security.jwt2.domain.document.User;
import com.security.jwt2.domain.dto.user.UserDto;
import com.security.jwt2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    @Transactional(readOnly = true)
    public String login(UserDto userDto){
        User user = userRepository.findByLoginId(userDto.getLoginId())
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));

        if(!encoder.matches(userDto.getPassword(),user.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.createAccessToken(user.toDto());
    }
}
