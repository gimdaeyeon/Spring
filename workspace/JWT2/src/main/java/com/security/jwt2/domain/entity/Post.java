package com.security.jwt2.domain.entity;

import com.security.jwt2.domain.base.Period;
import com.security.jwt2.domain.dto.post.PostDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_POST")
@Getter @Setter @ToString
@SequenceGenerator(
        name = "SEQ_POST_GENERATOR",
        sequenceName = "SEQ_POST",
        allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Period {
    @Id @GeneratedValue(generator ="SEQ_POST_GENERATOR")
    private Long id;
    private String title;
    private String content;

    @Builder
    public Post(String content, Long id, String title) {
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
