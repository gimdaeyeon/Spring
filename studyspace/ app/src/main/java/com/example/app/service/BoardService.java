package com.example.app.service;

import com.example.app.dto.BoardDto;
import com.example.app.dto.UserDto;
import com.example.app.mapper.BoardMapper;
import com.example.app.mapper.UserMapper;
import com.example.app.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
//     추가
    public void register(BoardDto boardDto){
        if(boardDto==null){
            throw new IllegalArgumentException("게시물 정보 누락");
        }
        boardMapper.insert(boardDto);
    }
//    삭제
    public void remove(Long boardNumber){
        if(boardNumber==null){
            throw new IllegalArgumentException("존재하지 않는 게시물");
        }
        boardMapper.delete(boardNumber);
    }
//    수정
    public void modify(BoardDto boardDto){
        if(boardDto==null){
            throw new IllegalArgumentException("게시물 수정 정보가 없습니다.");
        }
        boardMapper.update(boardDto);
    }
//    조회
    public BoardVo findBoard(Long boardNumber){
        if(boardNumber==null){
            throw new IllegalArgumentException("게시물 번호가 없습니다.");
        }
        return Optional.ofNullable(boardMapper.select(boardNumber))
                .orElseThrow(()->{
                    throw new IllegalArgumentException("존재하지 않는 게시물 번호");
                });
    }

//   전체 조회
    public List<BoardVo> findAll(){
        return boardMapper.selectAll();
    }

}
