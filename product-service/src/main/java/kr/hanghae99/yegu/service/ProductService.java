package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.controller.dto.ProductFeignResponseDto;
import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.dto.ProductAddRequestDto;
import kr.hanghae99.yegu.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<ProductFeignResponseDto> findAllById(List<Long> productIds) {
        return productRepository.findAllById(productIds)
                .stream()
                .map(ProductFeignResponseDto::new)
                .toList();
    }

    public Product findById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 검색할 수 없습니다. id = " + id));
    }

    @Transactional
    public void addProduct(ProductAddRequestDto dto) {
        productRepository.save(dto.toEntity());
    }
}
