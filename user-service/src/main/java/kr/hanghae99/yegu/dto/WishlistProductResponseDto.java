package kr.hanghae99.yegu.dto;

import lombok.*;

@NoArgsConstructor
@Getter
public class WishlistProductResponseDto {
    private Long productId;
    private String name;
    private int quantity;
    private int price;
    private String productUrl;
    private String thumbnailUrl;

    @Builder
    public WishlistProductResponseDto(Long productId, String name, int quantity, int price, String thumbnailUrl, String host) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
        this.quantity = quantity;
        this.productUrl = host + "/products/" + productId;
    }
}
