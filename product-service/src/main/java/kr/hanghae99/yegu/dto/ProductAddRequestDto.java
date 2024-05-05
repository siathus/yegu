package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO: 카테고리 설정
@AllArgsConstructor
@Getter
public class ProductAddRequestDto {
    private String name;
    private int price;
    private int stock;
    private String description;
    private String thumbnailUrl;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .description(description)
                .thumbnailUrl(thumbnailUrl)
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
}
