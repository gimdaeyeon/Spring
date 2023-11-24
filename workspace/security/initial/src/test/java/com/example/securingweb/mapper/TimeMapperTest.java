package com.example.securingweb.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TimeMapperTest {
    // 인터페이스를 implements한 클래스는 존재하지 않지만 우리가 @Mapper로
    // 등록했기 때문에 Spring이 구현체를 만들어 주입을 해준다.
    @Autowired
    private TimeMapper timeMapper;

    @Test
    @DisplayName("연결테스트")
    public void getTimeTest() {
        log.info("***** time : " + timeMapper.getTime());


    }

    @Test
    public void getTimeTest2() {
        log.info("***** time : " + timeMapper.getTime2());
    }
}