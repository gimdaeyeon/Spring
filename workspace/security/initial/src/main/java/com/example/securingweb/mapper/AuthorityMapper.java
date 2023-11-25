package com.example.securingweb.mapper;

import com.example.securingweb.domain.AuthorityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    void insert(AuthorityDto authorityDto);
    List<AuthorityDto> selectByUserId(Long userId);
}
