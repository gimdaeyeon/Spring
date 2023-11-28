package com.security.jwt.service;

import com.security.jwt.domain.entity.User;
import com.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(User user){
        if (user == null) {
            throw new IllegalArgumentException("회원 정보 누락");
        }
        passwordEncryption(user);
        userRepository.saveDefaultUserAuthority(userRepository.save(user));
    }
    private void passwordEncryption(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }


}






