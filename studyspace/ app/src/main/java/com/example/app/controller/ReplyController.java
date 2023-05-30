package com.example.app.controller;

import com.example.app.dto.ReplyDto;
import com.example.app.service.ReplyService;
import com.example.app.vo.Criteria;
import com.example.app.vo.PageVo;
import com.example.app.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {
    private final ReplyService replyService;
//    게시물 하나의 댓글들 조회
    @GetMapping("/list/{boardNumber}")
    public List<ReplyVo> getReplyList(@PathVariable("boardNumber")Long boardNumber){
        return replyService.findAll(boardNumber);
    }

//    댓글 작성
    @PostMapping("/reply")
    public void replyRegister(ReplyDto replyDto, HttpServletRequest req){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        replyDto.setUserNumber(userNumber);

        replyService.register(replyDto);
    }
//    댓글 수정
    @PatchMapping("/{replyNumber}")
    public void replyModify(@PathVariable("replyNumber")Long replyNumber,
                            @RequestBody ReplyDto replyDto){
        replyDto.setReplyNumber(replyNumber);
        replyService.modify(replyDto);
    }
//    댓글 삭제
    @DeleteMapping("{replyNumber}")
    public void replyRemove(@PathVariable("replyNumber")Long replyNumber){
        replyService.remove(replyNumber);
    }
//    댓글 1개 조회
    @GetMapping("{replyNumber}")
    public ReplyVo findReply(@PathVariable("replyNumber")Long replyNumber){
        return replyService.findOne(replyNumber);
    }

    //    댓글 무한 페이징
    @GetMapping("/list/{boardNumber}/{page}")
    public Map<String,Object> replyListPage(@PathVariable("boardNumber") Long boardNumber,
                                       @PathVariable("page") int page) {
        Criteria criteria = new Criteria(page,10);

        PageVo pageVo = new PageVo(criteria,replyService.findTotal(boardNumber));
        List<ReplyVo> replyList = replyService.findListPage(criteria, boardNumber);
        Map<String,Object> replyMap = new HashMap<>();
        replyMap.put("pageVo", pageVo);
        replyMap.put("replyList",replyList);

        return replyMap;
    }



}
