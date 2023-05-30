package com.example.app.service;

import com.example.app.dto.ReplyDto;
import com.example.app.dto.UserDto;
import com.example.app.mapper.ReplyMapper;
import com.example.app.mapper.UserMapper;
import com.example.app.vo.Criteria;
import com.example.app.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    //    댓글 작성
    public void register(ReplyDto replyDto){
        if(replyDto==null){
            throw new IllegalArgumentException("댓글 정보 누락");
        }
        replyMapper.insert(replyDto);
    }
    //    게시글 댓글 조회
    public List<ReplyVo>findAll(Long boardNumber){
        if(boardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락");
        }
        return replyMapper.selectList(boardNumber);
    }
    //    댓글 1개 조회
    public ReplyVo findOne(Long replyNumber){
        if(replyNumber==null){
            throw new IllegalArgumentException("댓글 번호 누락");
        }
        return Optional.ofNullable(replyMapper.select(replyNumber))
                .orElseThrow(()->{
                    throw new IllegalArgumentException("해당 번호의 댓글이 존재 하지 않습니다.");
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
        if(replyNumber==null){
            throw new IllegalArgumentException("댓글 번호 누락");
        }
        replyMapper.delete(replyNumber);
    }
    //    댓글 페이징
    public List<ReplyVo> findListPage(Criteria criteria, Long boardNumber){
        return replyMapper.selectListPage(criteria,boardNumber);
    }


    //    해당 게시물 총 댓글 수 조회
    public int findTotal(Long boardNumber){
        return replyMapper.selectTotal(boardNumber);
    }

}
