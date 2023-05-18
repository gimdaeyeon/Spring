package com.example.ex04.mapper;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.dto.ProductDto;
import com.example.ex04.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;    //테스트 라이브러리

@SpringBootTest
@Transactional
@Slf4j
class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    private OrderDto orderDto;
    private UserDto userDto;
    private ProductDto productDto;

    @BeforeEach
    void setUp(){
        userDto = new UserDto();
        userDto.setUserAge(3);
        userDto.setUserName("김철수");
        userDto.setUserAddress("강남역");

        productDto = new ProductDto();
        productDto.setProductName("아이폰");
        productDto.setProductPrice(120000);

        userMapper.insert(userDto);
        productMapper.insert(productDto);

        orderDto = new OrderDto();
        orderDto.setUserNumber(userDto.getUserNumber());
        orderDto.setProductNumber(productDto.getProductNumber());
        orderDto.setOrderCnt(5);

        orderMapper.insert(orderDto);

    }

    //   주문 등록 및 회원번호로 주문조회
    @Test
    @DisplayName("주문등록 및 회원 번호로 주문조회🌟🌟🌟🌟🌟🌟🌟")
    void selectByUserNumber() {

        List<OrderDto> orderList = orderMapper.selectByUserNumber(orderDto.getUserNumber());

        assertThat(orderList.get(0).getUserNumber()).isEqualTo(orderDto.getUserNumber());
        assertThat(orderList.size()).isEqualTo(1);
    }

//    주문수량 변경
    @Test
    @DisplayName("주문 수량 변경")
    void update() {
        int check = orderDto.getOrderCnt();

        orderDto.setOrderCnt(2);
        orderMapper.update(orderDto);

        assertThat(check).isNotEqualTo(orderDto.getOrderCnt());
    }


//    주문 삭제
    @Test
    @DisplayName("주문취소")
    void delete() {
        orderMapper.delete(orderDto);

        List<OrderDto> orderList = orderMapper.selectByUserNumber(orderDto.getUserNumber());

        assertThat(orderList.size()).isEqualTo(0);
    }
}