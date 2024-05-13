package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

// TODO: 카테고리 설정
@AllArgsConstructor
@Getter
public class ProductAddRequestDto {
    private String name;
    private int price;
    private int stock;
    private String description;
    private String thumbnailUrl;
    private boolean isPreorder;
    private LocalDateTime preorderStartTime;
    private LocalDateTime preorderEndTime;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .description(description)
                .thumbnailUrl(thumbnailUrl)
                .isPreorder(isPreorder)
                .preorderStartTime(preorderStartTime)
                .preorderEndTime(preorderEndTime)
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
}
