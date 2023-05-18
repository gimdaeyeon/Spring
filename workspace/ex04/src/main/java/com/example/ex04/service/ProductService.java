package com.example.ex04.service;

import com.example.ex04.dto.ProductDto;
import com.example.ex04.mapper.ProductMapper;
import lombok.NonNull;
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
                // @NonNull 매개변수에 들어오는 값이 null이면 자동으로 오류를 발생시켜주는 어노테이션
                // 라이브러리 의존성이 강하기 때문에 현재 국비 과정에서는 지양(if문으로 대체)
    public ProductDto find(Long productNumber){
        if(productNumber==null){
            throw new IllegalArgumentException("상품번호를 입력하세요");
        }

        return Optional.ofNullable(productMapper.select(productNumber)).orElseThrow(
                () -> { throw new IllegalArgumentException("존재하지않는 상품입니다");
        });
    }

    //    전체 상품 조회
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productMapper.selectAll();
    }

    //    상품 등록
    public void register(ProductDto productDto){
        if(productDto==null){
            throw new IllegalArgumentException("상품정보 누락");
        }

        productMapper.insert(productDto);
    }

    //    상품 삭제
    public void remove(Long productNumber)  {
        if(productNumber==null){
            throw new IllegalArgumentException("상품 번호 입력해라");
        }
        productMapper.delete(productNumber);
    }

    //    상품 정보 변경
    public void modify(ProductDto productDto) {
        if(productDto==null){
            throw new IllegalArgumentException("상품 정보를 입력해라!");
        }
        productMapper.update(productDto);
    }

}