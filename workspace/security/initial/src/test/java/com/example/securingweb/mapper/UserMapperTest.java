package com.example.securingweb.mapper;

import com.example.securingweb.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Slf4j
@Transactional
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void saveAndSelect(){
        UserDto user = new UserDto();
        user.setLoginId("aaa");
        user.setPassword("1234");
        user.setName("홍길동");
        user.setAge(15);

        userMapper.insert(user);

        UserDto foundUser = userMapper.selectById(user.getId());

        System.out.println(user);
        System.out.println(foundUser);

        assertThat(user).isEqualTo(foundUser);

    }


}