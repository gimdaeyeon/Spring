package com.security.jwt2.domain.dto.post;

import com.security.jwt2.domain.document.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String content;

    public PostDto(String content, String id, String title) {
        this.content = content;
        this.id = id;
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
