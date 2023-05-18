package com.example.ex04.mapper;

import com.example.ex04.dto.OrderDto;
import com.example.ex04.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
//   회원 번호로 주문조회
    List<OrderDto> selectByUserNumber(Long userNumber);
//    주문등록
    void insert(OrderDto orderDto);
//    주문수량 변경
    void update(OrderDto orderDto);
//    주문 삭제
    void delete(OrderDto orderDto);
//    dto나 vo를 쓰기 애매한 상황일 경우 @Param을 사용하는것을 고려해본다.
//    void delete2(@Param("userNumber") Long userNumber,@Param("userNumber") Long productNumber);
}
