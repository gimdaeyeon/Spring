package com.example.ex03.mapper;

import com.example.ex03.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;
    private MemberDto member;

    @BeforeEach
    void setUp(){
        member= new MemberDto();
        member.setMemberId("ooo");
        member.setMemberPassword("4444");
        member.setMemberName("아리스토필라테스");
    }


    @Test
    void insert() {
        memberMapper.insert(member);
    }

    @Test
    void selectNumber() {
        log.info("===memberNumber=== : " +memberMapper.selectNumber(member).toString());
    }

    @Test
    void insert2(){
        memberMapper.insert2(member);
        log.info("number : "+ member.getMemberNumber());
    }

    @Test
    void selectAll(){
        List<MemberDto> memberList = memberMapper.selectAll();
        log.info("memberList"+memberList.toString());
    }
    @Test
    void selectMemberByName(){
        MemberDto member = memberMapper.selectByName("장종필");
        log.info(member.toString());
    }

}