package com.example.ex03.service;

import com.example.ex03.dto.MemberDto;
import com.example.ex03.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
//회원 추가
    @Override
    public void addMember(MemberDto memberDto) throws IllegalArgumentException{
        memberMapper.insert(Optional.ofNullable(memberDto)
                .orElseThrow(()->{
                throw new IllegalArgumentException("회원정보를 올바르게 입력해주세요");
                })
        );
    }

//    회원정보로 회원번호 가져오기(로그인?)
    @Override
    public Long findNumber(MemberDto memberDto) throws IllegalArgumentException{
        return Optional.ofNullable(memberMapper.selectNumber(memberDto))
                .orElseThrow(()->{
                    throw new IllegalArgumentException("회원정보가 존재하지 않습니다.");
                });
    }
// 전체 회원정보 가져오기
    @Override
    public List<MemberDto> memberList() {
        return memberMapper.selectAll();
    }

//   이름으로 회원정보 가져오기
    @Override
    public MemberDto findMember(String memberName) throws IllegalArgumentException{
        return Optional.ofNullable(memberMapper.selectByName(memberName))
                .orElseThrow(()->{
                    throw new IllegalArgumentException("존재하지 않는 회원 이름입니다.");
                });
    }
}
