package com.example.ex06.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/community")
    public String community(){
        return "board/communityBoard";
    }

    @GetMapping("/qna")
    public String qna(){
        return "board/qnaBoard";
    }




}
