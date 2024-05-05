package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.dto.ProductAddRequestDto;
import kr.hanghae99.yegu.dto.ProductListResponseDto;
import kr.hanghae99.yegu.dto.ProductResponseDto;
import kr.hanghae99.yegu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
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

    @PostMapping("")
    public ResponseEntity<Void> addProduct(@RequestBody ProductAddRequestDto requestDto) {
        productService.addProduct(requestDto);
        return ResponseEntity
                .ok()
                .build();
    }
}