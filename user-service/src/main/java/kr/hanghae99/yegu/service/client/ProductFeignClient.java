package kr.hanghae99.yegu.service.client;

import kr.hanghae99.yegu.service.client.dto.ProductFeignResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service", path = "/api/internal/products")
public interface ProductFeignClient {
    @GetMapping
    List<ProductFeignResponseDto> findAllByProductId(@RequestParam List<Long> productIds);
}
