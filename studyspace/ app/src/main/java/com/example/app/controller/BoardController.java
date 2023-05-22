package com.example.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/list")
    public String showBoardList(){

        return "board/board";
    }

    @GetMapping("/write")
    public String boardWrite(){
        return "board/boardWrite";
    }


    @GetMapping("view")
    public String boardView(){
        return "board/boardView";
    }



}
