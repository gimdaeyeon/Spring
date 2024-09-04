package com.security.jwt2.service;

import com.security.jwt2.domain.document.User;
import com.security.jwt2.domain.dto.user.UserDto;
import com.security.jwt2.exception.UserAlreadyExistsException;
import com.security.jwt2.repository.UserRepository;
import com.security.jwt2.security.CustomUserDetails;
import com.security.jwt2.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserDto register(UserDto userDto) throws UserAlreadyExistsException {
        if(userRepository.existsByLoginId(userDto.getLoginId())){
            throw new UserAlreadyExistsException("이미 존재하는 유저id");
        }
        userDto.setPassword(encodePassword(userDto.getPassword()));
        User savedUser = userRepository.save(userDto.toEntity());
        return savedUser.toDto();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

        return new CustomUserDetails(user.toDto());
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserDto login(UserDto userDto){
        User user = userRepository.findByLoginId(userDto.getLoginId())
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));

        if(!encoder.matches(userDto.getPassword(),user.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return user.toDto();
    }


    private String encodePassword(String password){
        return encoder.encode(password);
    }
}
