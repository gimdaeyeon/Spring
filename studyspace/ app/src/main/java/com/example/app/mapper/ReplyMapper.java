package com.example.app.mapper;

import com.example.app.dto.ReplyDto;
import com.example.app.vo.Criteria;
import com.example.app.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper {
//    댓글 작성
    public void insert(ReplyDto replyDto);
//    게시글 댓글 조회
    public List<ReplyVo> selectList(Long boardNumber);
//    댓글 1개 조회
    public ReplyVo select(Long replyNumber);
//    댓글 수정
    public void update(ReplyDto replyDto);
//    댓글 삭제
    public void delete(Long replyNumber);
//    댓글 페이징
    public List<ReplyVo> selectListPage(@Param("criteria")Criteria criteria,@Param("boardNumber")Long boardNumber);
//    해당 게시물 총 댓글 수 조회
    public int selectTotal(Long boardNumber);

}
