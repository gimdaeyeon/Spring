package com.security.jwt2.domain.document;

import com.security.jwt2.domain.base.Period;
import com.security.jwt2.domain.dto.post.PostDto;
import jakarta.persistence.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("vue_post")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Period {
    @Id
    private String id;
    private String title;
    private String content;

    @Builder
    public Post(String content, String id, String title) {
        this.content = content;
        this.id = id;
        this.title = title;
    }

    public PostDto toDto(){
        PostDto dto = new PostDto();
        dto.setId(id);
        dto.setTitle(title);
        dto.setContent(content);
        return dto;
    }



}
