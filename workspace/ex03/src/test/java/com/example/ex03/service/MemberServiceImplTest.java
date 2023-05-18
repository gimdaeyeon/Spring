package com.example.ex03.service;

import com.example.ex03.dto.MemberDto;
import com.example.ex03.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
class MemberServiceImplTest {
    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberServiceImpl memberService;
    private MemberDto memberDto;

    @BeforeEach
    void setUp(){
        memberDto = new MemberDto();
        memberDto.setMemberId("bbb");
        memberDto.setMemberPassword("2222");
        memberDto.setMemberName("장삐쭈");
    }

    @Test
    @DisplayName("이름으로 회원정보 찾기")
    void findMember() {
        doReturn(memberDto).when(memberMapper).selectByName(any(String.class));

        MemberDto foundMember = memberService.findMember("fsdf");

        assertThat(foundMember.getMemberNumber()).isEqualTo(memberDto.getMemberNumber());
        assertThatThrownBy(()->memberService.findMember(null))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("누락");
    }

    @Test
    @DisplayName("이름으로 회원정보 찾기 : 해당회원이름이 존재하지 않을때 예외")
    void findMember2() {
        doReturn(null).when(memberMapper).selectByName(any(String.class));

        assertThatThrownBy(()-> memberService.findMember("김아무개"))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("존재하지");

    }


    //    회원추가
    @Test
    @DisplayName("회원가입 테스트")
    void addMember() {
        doNothing().when(memberMapper).insert2(any(MemberDto.class));

        memberService.addMember(memberDto);

        verify(memberMapper, times(1)).insert2(any(MemberDto.class));
    }

    //    아이디, 비밀번호로 회원번호 조회
    @Test
    void findNumber() {
        doReturn(memberDto.getMemberNumber()).when(memberMapper).selectNumber(any(MemberDto.class));

        assertThatThrownBy(()->memberService.findNumber(memberDto))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("존재하지");
    }

    @Test
    @DisplayName("전체 회원 조회")
    void memberList() {
        doReturn(List.of(memberDto)).when(memberMapper).selectAll();

        assertThat(memberService.memberList().size()).isEqualTo(1);
    }
}