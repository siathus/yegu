package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.controller.dto.ProductFeignResponseDto;
import kr.hanghae99.yegu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/internal/products")
@RestController
public class ProductInternalApiController {

    private final ProductService productService;

    @GetMapping("")
    public List<ProductFeignResponseDto> getAllProductsById(@RequestParam List<Long> productIds) {
        return productService.findAllById(productIds);
    }

    @GetMapping("/{productId}")
    public ProductFeignResponseDto getProductById(@PathVariable Long productId) {
        return new ProductFeignResponseDto(productService.findById(productId));
    }
}
