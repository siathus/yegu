package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String description;
    private String thumbnailUrl;
    private String categoryName;
    private boolean isPreorder;
    private LocalDateTime preorderStartTime;
    private LocalDateTime preorderEndTime;
    private String status;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.description = product.getDescription();
        this.thumbnailUrl = product.getThumbnailUrl();
        this.categoryName = product.getCategory().getName();
        this.isPreorder = product.isPreorder();
        this.preorderStartTime = product.getPreorderStartTime();
        this.preorderEndTime = product.getPreorderEndTime();
        this.status = product.getStatus().name();
    }
}
