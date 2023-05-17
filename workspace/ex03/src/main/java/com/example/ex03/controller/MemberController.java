package com.example.ex03.controller;

import com.example.ex03.dto.MemberDto;
import com.example.ex03.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public void join(MemberDto memberDto) {
        memberService.addMember(memberDto);
    }

//    @PostMapping("/join")
//    public String join(MemberDto memberDto){
//        memberService.addMember(memberDto);
//        return "member/login";
//    }
@GetMapping("/login")
public String login(MemberDto memberDto, HttpServletRequest req) {
    Optional<Long> memberNumber = Optional.ofNullable(memberService.findNumber(memberDto));
   if(memberNumber.isPresent()){
       req.getSession().setAttribute("memberNumber", memberNumber.get());
       return "main";
   }

   return "member/login";
}


//    @GetMapping("/login")
//    public String login(MemberDto memberDto, HttpServletRequest req) {
//        Optional<Long> memberNumber = memberService.findNumber(memberDto);
//        Long num = null;
//        try {
//            num = memberNumber.orElseThrow(() -> {
//                throw new IllegalArgumentException("잘못된 회원정보입니다.");
//            });
//            req.getSession().setAttribute("memberNumber", num);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//            return "member/login";
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        return "main";
//    }

//    @PostMapping("/login")
//    public String login(MemberDto memberDto, HttpServletRequest req){
//        Long memberNumber = memberService.findNumber(memberDto);
//        req.getSession().setAttribute("memberNumber", memberNumber);
//        return "main";
//    }
}





