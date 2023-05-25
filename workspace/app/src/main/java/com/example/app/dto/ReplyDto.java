package com.example.app.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor @NoArgsConstructor
public class ReplyDto {
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

//    @Builder
//    public ReplyDto(String replyContent,String replyRegisterDate,String replyUpdateDate, Long boardNumber, Long userNumber) {
//        this.replyContent = replyContent;
//        this.replyRegisterDate = replyRegisterDate;
//        this.replyUpdateDate = replyUpdateDate;
//        this.boardNumber = boardNumber;
//        this.userNumber = userNumber;
//    }
}
