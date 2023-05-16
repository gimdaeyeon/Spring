package com.example.ex03.mapper;

import com.example.ex03.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//만약 dao를 쓴다면
//@Repository 이 주석을 사용한다.

//만들었으면 무조건 테스트
@Mapper
public interface MemberMapper {
//    회원추가
    void insert(MemberDto memberDto);
//    아이디, 비밀번호로 회원번호 조회
    public Long selectNumber(MemberDto memberDto);
//    회원추가2
    public void insert2(MemberDto memberDto);
//    전체 회왼 조회
    public List<MemberDto> selectAll();
//    이름으로 해당 회원정보 조회
    public MemberDto selectByName(String memberName);




}
