package com.example.ex03.mapper;

import com.example.ex03.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;
    private MemberDto memberDto;

    @BeforeEach
    void setUp(){
        memberDto = new MemberDto();
        memberDto.setMemberId("ooo");
        memberDto.setMemberPassword("4444");
        memberDto.setMemberName("아리스토필라테스");
    }

    //    회원추가2 및 전체 회원 조회
    @Test
    void testInsert2() {
        memberMapper.insert(memberDto);

        List<MemberDto> memberDtoList = memberMapper.selectAll();

        assertThat(memberDtoList.size()).isEqualTo(1);
        assertThat(memberDtoList.get(0).getMemberName()).isEqualTo("아리스토필라테스");
    }

//    아이디, 비밀번호로 회원번호 조회
    @Test
    void testSelectNumber() {
        memberMapper.insert2(memberDto);

        assertThat(memberDto.getMemberNumber()).isEqualTo(memberMapper.selectNumber(memberDto));
    }


//    이름으로 해당 회원정보 조회
    @Test
    void selectByName() {
        memberMapper.insert2(memberDto);

        assertThat(memberDto.getMemberNumber())
                .isEqualTo(memberMapper.selectByName(memberDto.getMemberName()).getMemberNumber());

    }
}
