package com.example.ex04.service;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
class OrderServiceTest {
    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;
    private OrderDto orderDto;

    @BeforeEach
    void setUp(){
        orderDto = new OrderDto();
        orderDto.setUserNumber(2L);
        orderDto.setProductNumber(4L);
        orderDto.setOrderCnt(3);
    }

    @Test
    @DisplayName("회원 이름으로 주문정보 조회")
    void findByUserNumber() {
        doReturn(List.of(orderDto)).when(orderMapper).selectByUserNumber(any(Long.class));

        List<OrderDto> orderDtoList = orderService.findByUserNumber(3L);

        assertThat(orderDtoList.size()).isEqualTo(1);
        assertThat(orderDtoList).contains(orderDto);
        assertThatThrownBy(()->orderService.findByUserNumber(null))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("회원 번호");
    }

    @Test
    @DisplayName("주문 저장")
    void register() {
        doNothing().when(orderMapper).insert(any(OrderDto.class));

        orderService.register(orderDto);

        verify(orderMapper, times(1)).insert(orderDto);
    }

    @Test
    @DisplayName("주문 변경")
    void modify() {
        doNothing().when(orderMapper).update(any(OrderDto.class));

        orderService.modify(orderDto);

        verify(orderMapper, times(1)).update(orderDto);
    }

    @Test
    @DisplayName("주문 취소")
    void remove() {
        doNothing().when(orderMapper).delete(any(OrderDto.class));

        orderService.remove(orderDto);

        verify(orderMapper, times(1)).delete(orderDto);
    }
}