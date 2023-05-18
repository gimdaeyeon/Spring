package com.example.ex04.mapper;

import com.example.ex04.dto.UserDto;
import static org.assertj.core.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    private UserDto userDto;

    @BeforeEach
    void setUp(){
        userDto = new UserDto();
        userDto.setUserAge(22);
        userDto.setUserName("홍길동");
        userDto.setUserAddress("강남구");
    }

    @Test
    @DisplayName("회원 삽입 테스트")
    void insertAndSelect() {
        userMapper.insert(userDto);
        UserDto foundUser = userMapper.select(userDto.getUserName());

        log.info(foundUser.toString());
        assertThat(userDto.getUserName()).isEqualTo(foundUser.getUserName());
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        userMapper.insert(userDto);
        UserDto foundUser = userMapper.select("홍길동");
        userMapper.delete(foundUser.getUserNumber());

        assertThat(userMapper.selectAll().size()).isEqualTo(0);
    }
}