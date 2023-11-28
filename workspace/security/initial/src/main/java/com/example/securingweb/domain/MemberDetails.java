package com.example.securingweb.domain;

import com.example.securingweb.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemberDetails implements UserDetails {
    private final UserDto userDto;
    private UserService userService;

    public MemberDetails(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();

        List<AuthorityDto> list = userService.findAuthorityById(userDto.getId());

        for(AuthorityDto auth : list){
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return auth.getAuthorityName();
                }

                // thymeleaf 등에서 확인 활용하기 위하 문자열 (학습목적)
                @Override
                public String toString() {
                    return auth.getAuthorityName();
                }
            });
        }

        return collect;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getLoginId();
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
        return userDto.getId();
    }

    public String getRealName(){
        return userDto.getName();
    }
}
