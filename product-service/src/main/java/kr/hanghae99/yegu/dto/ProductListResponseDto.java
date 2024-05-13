package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ProductListResponseDto {
    private Long id;
    private String name;
    private int price;
    private String categoryName;
    private boolean isPreorder;
    private LocalDateTime preorderStartTime;
    private LocalDateTime preorderEndTime;

    public ProductListResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryName = product.getCategory().getName();
        this.isPreorder = product.isPreorder();
        this.preorderStartTime = product.getPreorderStartTime();
        this.preorderEndTime = product.getPreorderEndTime();
    }
}
