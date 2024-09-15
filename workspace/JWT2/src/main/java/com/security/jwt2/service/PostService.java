package com.security.jwt2.service;

import com.security.jwt2.domain.dto.post.PostDto;
import com.security.jwt2.domain.entity.Post;
import com.security.jwt2.exception.AlreadyExistsException;
import com.security.jwt2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostDto> getPostAll() {
        return postRepository.findAll()
                .stream()
                .map(Post::toDto)
                .toList();
    }

    public PostDto register(PostDto postDto) throws AlreadyExistsException {
        if (postRepository.existsByTitle(postDto.getTitle())) {
            throw new AlreadyExistsException("이미 존재한는 게시글 입니다.");
        }
        Post savedPost = postRepository.save(postDto.toEntity());
        return savedPost.toDto();
    }

    @Transactional(readOnly = true)
    public PostDto getPostOne(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"))
                .toDto();
    }

    public void modifyPost(PostDto postDto){
        Post post = postRepository.findById(postDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
    }

    public void removePost(Long postId) {
        postRepository.delete(
                postRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."))
        );
    }

}
