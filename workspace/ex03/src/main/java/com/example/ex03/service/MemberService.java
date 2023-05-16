package com.example.ex03.service;

import com.example.ex03.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberService {
//    추가
    public void addMember(MemberDto memberDto);
//    번호 조회
    public Optional<Long> findNumber(MemberDto memberDto);
//    전체 회원 조회
    public List<MemberDto> memberList();
//    회원 이름으로 해당회원 조회
    public MemberDto findMember(String memberName);
}
