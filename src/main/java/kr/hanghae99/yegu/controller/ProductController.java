package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.dto.ProductListResponseDto;
import kr.hanghae99.yegu.dto.ProductResponseDto;
import kr.hanghae99.yegu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<ProductListResponseDto> productList() {
        return productService.findAll()
                .stream()
                .map(ProductListResponseDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDto productDetail(@PathVariable Long id) {
        return new ProductResponseDto(productService.findById(id));
    }
}
