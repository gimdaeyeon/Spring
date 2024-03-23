package com.security.jwt.service;

import com.security.jwt.domain.entity.User;
import com.security.jwt.repository.UserRepository;
import com.security.jwt.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(()->new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getAuthorities()
                .forEach((authority)->authorities.add(
                        (GrantedAuthority) () -> authority.getAuthority().name())
                );
        return new org.springframework
                .security.core.userdetails.User(username,user.getPassword(),authorities);
    }
    public void register(User user){
        if (user == null) {
            throw new IllegalArgumentException("회원 정보 누락");
        }
        passwordEncryption(user);
        userRepository.saveDefaultUserAuthority(userRepository.save(user));
    }
    public String authenticateAndGetJwt(String loginId,String password){
        UserDetails user = loadUserByUsername(loginId);

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UsernameNotFoundException("비밀번호가 일치하지 않습니다.");
        }
        String token =jwtTokenProvider.createToken(loginId,user.getAuthorities());
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return token;
    }


    private void passwordEncryption(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}






