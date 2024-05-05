package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.controller.dto.ProductFeignResponseDto;
import kr.hanghae99.yegu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
