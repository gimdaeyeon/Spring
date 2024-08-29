package com.security.jwt2.service;

import com.security.jwt2.domain.dto.user.UserDto;
import com.security.jwt2.domain.entity.User;
import com.security.jwt2.repository.UserRepository;
import com.security.jwt2.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserDto userDto){
        userDto.setPassword(encodePassword(userDto.getPassword()));
        userRepository.save(userDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

        return new CustomUserDetails(user.toDto());
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
