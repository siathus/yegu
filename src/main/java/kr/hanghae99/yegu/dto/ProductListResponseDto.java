package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductListResponseDto {
    private Long id;
    private String name;
    private int price;
    private String categoryName;

    public ProductListResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryName = product.getCategory().getName();
    }
}
