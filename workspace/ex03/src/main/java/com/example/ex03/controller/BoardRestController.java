package com.example.ex03.controller;

import com.example.ex03.dto.BoardDto;
import com.example.ex03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/v1/*")
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardService boardService;

    @PostMapping("/board")
    public String boardWrite(@RequestBody BoardDto boardDto){
        System.out.println(boardDto);
        return "success";
    }
    @GetMapping("/list")
    public BoardDto boardList(){
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardNumber(1L);
        boardDto.setBoardTitle("title");
        boardDto.setBoardContent("content");
        boardDto.setMemberId("aaaa");

        return boardDto;
    }

    @GetMapping("/{boardNumber}")
    public BoardDto boardDetail(@PathVariable("boardNumber") Long boardNumber){
//       boardService.findBoard(boardNumber);
        System.out.println(boardNumber + "====================");

        BoardDto boardDto = new BoardDto();
        boardDto.setBoardNumber(1L);
        boardDto.setBoardTitle("title");
        boardDto.setBoardContent("content");
        boardDto.setMemberId("aaaa");

        return boardDto;
    }





}
