package com.security.jwt.security;

import com.security.jwt.domain.entity.User;
import com.security.jwt.domain.entity.UserAuthority;
import com.security.jwt.domain.enumType.Authority;
import com.security.jwt.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrincipalDetails  implements UserDetails {
    private final User user;
    private UserService userService;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();

        List<UserAuthority> authority = user.getAuthority();

        for(UserAuthority auth : authority){
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return auth.getAuthority().name();
                }

                // thymeleaf 등에서 확인 활용하기 위하 문자열 (학습목적)
                @Override
                public String toString() {
                    return auth.getAuthority().name();
                }
            });
        }

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    // 계정이 만료되지 않았는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠긴건 아닌지?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 credential 이 만료된건 아닌지?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 활성화 되었는지?
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId(){
        return user.getId();
    }

    public String getRealName(){
        return user.getName();
    }
}