package com.example.ex05.controller;

import com.example.ex05.dto.UserDto;
import com.example.ex05.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

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
