package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.product.Product;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishlistProductResponseDto {
    private Long productId;
    private int quantity;
    private int price;
    private String productUrl;
    private String thumbnailUrl;

    public WishlistProductResponseDto(Product product, String host, int quantity) {
        this.productId = product.getId();
        this.price = product.getPrice();
        this.thumbnailUrl = product.getThumbnailUrl();
        this.quantity = quantity;
        this.productUrl = host + "/products/" + product.getId();
    }
}
