package com.example.app.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor @NoArgsConstructor
//@RequiredArgsConstructor는 final또는 NonNull이 붙은 필드만 포기화하느 생성자를 만든다.
public class ReplyVo {
    private Long replyNumber;
    @NonNull
    private String replyContent;
    @NonNull
    private String replyRegisterDate;
    @NonNull
    private String replyUpdateDate;
    @NonNull
    private Long boardNumber;
    @NonNull
    private Long userNumber;
    private String userId;


}
