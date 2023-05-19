package com.example.app.controller;

import com.example.app.service.BoardService;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board")
    public void board(){}

    @GetMapping("/boardWrite")
    public void boardWrite(){}

    @GetMapping("/boardView")
    public void boardView(){}
}
