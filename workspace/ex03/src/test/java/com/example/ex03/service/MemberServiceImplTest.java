package com.example.ex03.service;

import com.example.ex03.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@RequiredArgsConstructor
class MemberServiceImplTest {

    @Autowired
    private MemberServiceImpl memberService;
    private MemberDto memberDto;

    @BeforeEach
    void setUp(){
        memberDto = new MemberDto();
        memberDto.setMemberId("bbb");
        memberDto.setMemberPassword("2222");
        memberDto.setMemberName("장삐쭈");
    }


//    @Test
//    void addMember() {
//        memberService.addMember(memberDto);
//    }

//    @Test
//    void findNumber() {
//        Optional<Long> number = memberService.findNumber(memberDto);
//
//        log.info("---number---- : "+ number);
//    }

    @Test
    @DisplayName("이름으로 회원정보 찾기")
    void findMember() {
//        assert 활용해서 테스트코드 완성하기
        assertThat(memberService.findMember("장삐쭈").getMemberId())
                .isEqualTo(memberDto.getMemberId());
    }

    @Test
    @DisplayName("이름으로 회원정보 찾기 예외테스트")
    void findMemberExctptionTest() {
//        assert 활용해서 테스트코드 완성하기
        assertThatThrownBy(()->{
            memberService.findMember("일라오이");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("존재하지 않는 회원 이름입니다.");
    }








}