package com.security.jwt2.domain.dto.post;

import com.security.jwt2.domain.document.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostDto(String content, LocalDateTime createdDate, String id, LocalDateTime modifiedDate, String title) {
        this.content = content;
        this.createdDate = createdDate;
        this.id = id;
        this.modifiedDate = modifiedDate;
        this.title = title;
    }

    public Post toEntity(){
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }


}
