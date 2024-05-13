package kr.hanghae99.yegu.controller.dto;

import kr.hanghae99.yegu.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ProductFeignResponseDto {
    private Long productId;
    private String name;
    private int price;
    private String thumbnailUrl;
    private String categoryName;
    private boolean isPreorder;
    private LocalDateTime preorderStartTime;
    private LocalDateTime preorderEndTime;
    private int stock;

    public ProductFeignResponseDto(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.thumbnailUrl = product.getThumbnailUrl();
        this.isPreorder = product.isPreorder();
        this.preorderStartTime = product.getPreorderStartTime();
        this.preorderEndTime = product.getPreorderEndTime();
        this.stock = product.getStock();
    }
}
