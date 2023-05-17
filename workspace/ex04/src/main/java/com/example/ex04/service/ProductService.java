package com.example.ex04.service;

import com.example.ex04.dto.ProductDto;
import com.example.ex04.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    public final ProductMapper productMapper;

    //    상품번호로 상품조회
    @Transactional(readOnly = true)
    public ProductDto find(Long productNumber) throws IllegalArgumentException {
        Long number = Optional.ofNullable(productNumber).orElseThrow(() -> {
            throw new IllegalArgumentException("상품번호를 입력하세요");
        });

        return Optional.ofNullable(productMapper.select(number)).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지않는 상품입니다");
        });
    }



    //    전체 상품 조회
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productMapper.selectAll();
    }



    //    상품 등록
    public void register(ProductDto productDto) throws IllegalArgumentException {
        ProductDto product = Optional.ofNullable(productDto).orElseThrow(() -> {
            throw new IllegalArgumentException("상품정보 누락");
        });
        productMapper.insert(product);
    }



    //    상품 삭제
    public void remove(Long productNumber) throws IllegalArgumentException {
        Long number = Optional.ofNullable(productNumber).orElseThrow(() -> {
            throw new IllegalArgumentException("상품 번호 입력해라");
        });
        productMapper.delete(number);
    }

    ;

    //    상품 정보 변경
    public void modify(ProductDto productDto) throws IllegalArgumentException {
        ProductDto product = Optional.ofNullable(productDto).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("상품 정보를 입력해라!");
                }
        );
        productMapper.update(product);
    }

}