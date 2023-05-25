package com.example.app.service;

import com.example.app.dto.BoardDto;
import com.example.app.mapper.BoardMapper;
import com.example.app.vo.BoardVo;
import com.example.app.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;


    //     추가
    public void register(BoardDto boardDto){
        if(boardDto == null){
            throw new IllegalArgumentException("게시물 정보가 없습니다 누락(null)");
        }
        boardMapper.insert(boardDto);
    }
//    삭제
    public void remove(Long boardNumber){
        if(boardNumber==null){
            throw new IllegalArgumentException("존재하지 않는 게시물");
        }
        fileService.remove(boardNumber);
        boardMapper.delete(boardNumber);
    }
//    수정
    public void modify(BoardDto boardDto){
        if(boardDto == null){
            throw new IllegalArgumentException("게시물 수정 정보가 없습니다.");
        }
        boardMapper.update(boardDto);
    }

//    트랜젝션을 적용하여 하나의 쿼리에서 오류가 나면 모두 롤백시킨다.
//    주의사항 : @Trasactionl을 붙인 메소드에서 같은 클래스에 선언한 메소드를 사용하면 트랜젝션이 적용되지 않는다.
    public void modify(BoardDto boardDto, List<MultipartFile> files) throws IOException{
        if(boardDto==null||files==null){
            throw new IllegalArgumentException("게시글 수정 매개변수 null체크");
        }
        fileService.remove(boardDto.getBoardNumber());
        fileService.registerAndSaveFiles(files, boardDto.getBoardNumber());
        boardMapper.update(boardDto);
    }

//    조회
    /**
     *
     * @param boardNumber
     * @return
     * @throws IllegalArgumentException 게시물 번호가 존재하지 않으면 발생된다.
     */
    @Transactional(readOnly = true)
    public BoardVo findBoard(Long boardNumber){
        if(boardNumber==null){
        throw new IllegalArgumentException("게시물 번호가 없습니다.");
        }

        return Optional.ofNullable(boardMapper.select(boardNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 게시물 번호");});
    }
//   전체 조회
    @Transactional(readOnly = true)
    public List<BoardVo> findAll(Criteria criteria) {
        return boardMapper.selectAll(criteria);
    }
//    전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal(){
        return boardMapper.selectTotal();
    }


}
