package com.example.app.service;

import com.example.app.dto.ReplyDto;
import com.example.app.mapper.ReplyMapper;
import com.example.app.vo.Criteria;
import com.example.app.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyMapper replyMapper;

//        댓글 작성
    public void register(ReplyDto replyDto){
        if(replyDto==null){
            throw new IllegalArgumentException("댓글 정보 누락");
        }
        replyMapper.insert(replyDto);
    }
//    게시글 댓글 조회
    public List<ReplyVo> findList(Long boardNumber){
        if(boardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락");
        }
        return replyMapper.selectList(boardNumber);
    }


    public ReplyVo findReply(Long replyNumber){
        if (replyNumber == null) {
            throw new IllegalArgumentException("댓글 번호 누락");
        }
        return Optional.ofNullable(replyMapper.select(replyNumber))
                .orElseThrow(()->{
                    throw new IllegalArgumentException("존재하지 않는 댓글 번호");
                });
    }

//    댓글 수정
    public void modify(ReplyDto replyDto){
        if(replyDto==null){
            throw new IllegalArgumentException("댓글 수정 정보 누락");
        }
        replyMapper.update(replyDto);
    }

//    댓글 삭제
    public void remove(Long replyNumber){
        if (replyNumber == null) {
            throw new IllegalArgumentException("댓글 번호 누락");
        }
        replyMapper.delete(replyNumber);
    }
//    댓글 페이징
    public List<ReplyVo> findListPage(Criteria criteria, Long boardNumber){
        if (boardNumber == null||criteria==null) {
            throw new IllegalArgumentException("댓글 페이징 정보 누락");
        }
        return replyMapper.selectListPage(criteria, boardNumber);
    }

    public int findTotal(Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락");
        }

        return replyMapper.selectTotal(boardNumber);
    }





}
