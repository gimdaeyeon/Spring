package com.security.jwt2.service;

import com.security.jwt2.domain.document.User;
import com.security.jwt2.domain.dto.post.PostDto;
import com.security.jwt2.domain.dto.user.UserDto;
import com.security.jwt2.exception.AlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("mongodb 유저 등록 테스트")
    void registerUserTest() throws AlreadyExistsException {
        UserDto user = new UserDto();

        user.setLoginId("bb");
        user.setPassword("1234");
        user.setNickname("천태풍");

        userService.register(user);
    }

    @Test
    @DisplayName("유저 조회 테스트")
    void findAllTest(){
        List<User> users = userService.findAll();

        users.forEach(u->log.info("{}", u));
    }

    @Test
    @DisplayName("Post 등록 테스트")
    void registerPostTest() throws Exception{
        PostDto post = new PostDto();

        post.setTitle("제목");
        post.setContent("내용");

        postService.register(post);
        System.out.println(postService.getPostAll());
    }



}