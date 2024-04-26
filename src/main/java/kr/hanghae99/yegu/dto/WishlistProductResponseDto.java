package kr.hanghae99.yegu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishlistProductResponseDto {
    private Long productId;
    private int quantity;
    private int price;
    private String thumbnailUrl;
}
