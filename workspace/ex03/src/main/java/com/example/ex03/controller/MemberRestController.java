package com.example.ex03.controller;

import com.example.ex03.dto.BoardDto;
import com.example.ex03.dto.MemberDto;
import com.example.ex03.service.BoardService;
import com.example.ex03.service.MemberService;
import com.example.ex03.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members/v1/*")
@RequiredArgsConstructor
@Slf4j
public class MemberRestController {
    private final MemberServiceImpl memberService;

    @PostMapping("/memberList")
    public List<MemberDto> memberList(){
        return memberService.memberList();
    }

    @GetMapping("/{memberName}")
    public MemberDto searchMember(@PathVariable("memberName") String memberName){
        return memberService.findMember(memberName);
    }

}
