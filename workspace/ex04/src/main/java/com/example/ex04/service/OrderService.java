package com.example.ex04.service;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    public final OrderMapper orderMapper;

    //   회원 번호로 주문조회
    public List<OrderDto> findByUserNumber(Long userNumber){
        if(userNumber == null){
            throw new IllegalArgumentException("회원 번호 누락");
        }
        return orderMapper.selectByUserNumber(userNumber);
    }

    //    주문등록
    public void register(OrderDto orderDto){
        if(orderDto == null){
            throw new IllegalArgumentException("주문 정보 누락");
        }
        orderMapper.insert(orderDto);
    }
    //    주문수량 변경
    public void modify(OrderDto orderDto){
        if(orderDto == null){
            throw new IllegalArgumentException("주문 정보 누락");
        }
        orderMapper.update(orderDto);
    }
    //    주문 삭제
    public void remove(OrderDto orderDto){
        if(orderDto == null){
            throw new IllegalArgumentException("주문 정보 누락");
        }
        orderMapper.delete(orderDto);
    }

}