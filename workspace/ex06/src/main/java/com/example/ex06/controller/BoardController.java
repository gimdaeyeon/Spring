package com.example.ex06.controller;

import com.example.ex06.crawling.Crawling;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    private final Crawling crawling;

    @GetMapping("/community")
    public String community(Model model){
        model.addAttribute("list",crawling.doProcess());

        return "board/communityBoard";
    }

    @GetMapping("/qna")
    public String qna(){
        return "board/qnaBoard";
    }




}
