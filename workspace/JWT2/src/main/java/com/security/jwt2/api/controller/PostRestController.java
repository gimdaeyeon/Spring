package com.security.jwt2.api.controller;

import com.security.jwt2.domain.dto.post.PostDto;
import com.security.jwt2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts ")
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(){
        try {
            return ResponseEntity.ok(postService.getPostAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public void createPost(@RequestBody PostDto postDto){
        postService.register(postDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostOne(@PathVariable("postId")String postId){
        return ResponseEntity.ok(postService.getPostOne(postId));
    }

    @PutMapping("/{postId}")
    public void modifyPostOne(@PathVariable("postId")String postId,
                              @RequestBody PostDto postDto){
        postService.modifyPost(postDto);
    }

    @DeleteMapping("/{postId}")
    public void removePostOne(@PathVariable("postId")String postId){
        postService.removePost(postId);
    }

}
