package com.example.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/board")
    public void showBoardList(){

    }

    @GetMapping("/boardWrite")
    public void boardWrite(){

    }


    @GetMapping("/boardView")
    public void boardView(){}



}
