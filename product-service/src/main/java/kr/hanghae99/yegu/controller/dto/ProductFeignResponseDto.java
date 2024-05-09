package kr.hanghae99.yegu.controller.dto;

import kr.hanghae99.yegu.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductFeignResponseDto {
    private Long productId;
    private String name;
    private int price;
    private String thumbnailUrl;
    private String categoryName;
    private int stock;

    public ProductFeignResponseDto(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.thumbnailUrl = product.getThumbnailUrl();
        this.stock = product.getStock();
    }
}
