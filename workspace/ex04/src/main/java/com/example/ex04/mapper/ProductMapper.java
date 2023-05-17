package com.example.ex04.mapper;

import com.example.ex04.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
//    상품번호로 상품조회
    ProductDto select(Long productNumber);
//    전체 상품 조회
    List<ProductDto> selectAll();
//    상품 등록
    void insert(ProductDto productDto);
//    상품 삭제
    void delete(Long productNumber);
//    상품 정보 변경(이름, 가격)
    void update(ProductDto productDto);

}
