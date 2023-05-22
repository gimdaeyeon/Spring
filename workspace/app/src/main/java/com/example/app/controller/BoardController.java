package com.example.app.controller;

import com.example.app.dto.BoardDto;
import com.example.app.service.BoardService;
import com.example.app.service.UserService;
import com.example.app.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String showBoardList(Model model){
        List<BoardVo> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "board/board";
    }

    @GetMapping("/write")
    public String boardWrite(HttpServletRequest req){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");

        return userNumber==null ? "user/login" : "board/boardWrite";
    }

    @PostMapping("/write")
    public RedirectView boardWrite(BoardDto boardDto, HttpServletRequest req, RedirectAttributes redirectAttributes){
//        RedirectAttributes는 리다이렉트 전용 Model객체라고 생각하면 된다.
        Long userNumber =(Long)req.getSession().getAttribute("userNumber");
        boardDto.setUserNumber(userNumber);
        boardService.register(boardDto);

//        리다이렉트를 사용할 때 데이터를 전송하는 방법

//        쿼리스트링으로 데이터를 전송한다.(url에 쿼리스트링이 생긴다.)
//        redirectAttributes.addAttribute("boardNumber", boardDto.getBoardNumber());

//        플레쉬를 사용하여 데이터를 전송한다.
        redirectAttributes.addFlashAttribute("boardNumber", boardDto.getBoardNumber());

        return new RedirectView("/board/list");
    }

    @GetMapping("/view")
    public String boardView(Long boardNumber,Model model){
        BoardVo boardVo = boardService.findBoard(boardNumber);
        model.addAttribute("board", boardVo);
        return "board/boardView";
    }





}
