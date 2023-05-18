package com.example.ex04.service;

import com.example.ex04.dto.ProductDto;
import com.example.ex04.mapper.ProductMapper;
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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
class ProductServiceTest {
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;
    private ProductDto productDto;

    @BeforeEach
    void setUp(){
        productDto = new ProductDto();
        productDto.setProductName("개쩌는 노트북");
        productDto.setProductPrice(3000000);
    }

    @Test
    @DisplayName("상품번호로 상품 조회")
    void find() {
        doReturn(productDto).when(productMapper).select(any(Long.class));

        assertThat(productService.find(3L).getProductName())
                .isEqualTo(productDto.getProductName());
    }

    @Test
    @DisplayName("상품번호로 상품 조회 : 예외 확인")
    void find2() {
        doReturn(null).when(productMapper).select(any(Long.class));

        assertThatThrownBy(()->productService.find(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지");
    }

    @Test
    @DisplayName("전체 상품 조회확인")
    void findAll() {
        doReturn(List.of(productDto)).when(productMapper).selectAll();

        List<ProductDto> productDtoList = productService.findAll();

        assertThat(productDtoList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("상품등록")
    void register() {
        doNothing().when(productMapper).insert(any(ProductDto.class));

        productService.register(productDto);

        verify(productMapper, times(1)).insert(any(ProductDto.class));
    }
    @Test
    @DisplayName("상품 삭제 테스트")
    void remove() {
        doNothing().when(productMapper).delete(any(Long.class));

        productService.remove(54L);

        verify(productMapper, times(1)).delete(54L);
    }

    @Test
    @DisplayName("상품정보 변경 테스트")
    void modify() {
        doNothing().when(productMapper).update(any(ProductDto.class));

        productService.modify(productDto);

        verify(productMapper, times(1)).update(productDto);
    }
}