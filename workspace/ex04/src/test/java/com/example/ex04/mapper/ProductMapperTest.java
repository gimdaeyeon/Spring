package com.example.ex04.mapper;

import com.example.ex04.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;
    private ProductDto productDto;

    @BeforeEach
    void setUp(){
        productDto = new ProductDto();
//        productDto.setProductNumber(1L);
        productDto.setProductName("그램");
        productDto.setProductPrice(1500000);
    }

    @Test
    @DisplayName("전체 상품 조회")
    void selectAll() {
        productMapper.insert(productDto);
        productMapper.insert(productDto);
        List<ProductDto> productDtoList = productMapper.selectAll();
        assertThat(productDtoList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("상품 등록 및 조회")
    void insertAndSelect() {
//        given
        productMapper.insert(productDto);

//        when
        ProductDto foundProduct = productMapper.select(productDto.getProductNumber());

//        then
        assertThat(foundProduct.getProductNumber()).isEqualTo(productDto.getProductNumber());
    }

    @Test
    @DisplayName("상품 삭제")
    void delete() {
        productMapper.insert(productDto);
        productMapper.delete(productDto.getProductNumber());

        assertThat(productMapper.select(productDto.getProductNumber())).isNull();
    }

    @Test
    @DisplayName("상품 이름, 가격 변경")
    void update() {
        productMapper.insert(productDto);
        productDto.setProductName("무한의 대검");
        productDto.setProductPrice(3400);

        productMapper.update(productDto);

        ProductDto foundProduct = productMapper.select(productDto.getProductNumber());

        assertThat(foundProduct.getProductName())
                .isEqualTo(productDto.getProductName());
    }
}
