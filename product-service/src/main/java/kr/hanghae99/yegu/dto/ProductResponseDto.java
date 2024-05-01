package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String status;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.description = product.getDescription();
        this.thumbnailUrl = product.getThumbnailUrl();
        this.categoryName = product.getCategory().getName();
        this.status = product.getStatus().name();
    }
}
