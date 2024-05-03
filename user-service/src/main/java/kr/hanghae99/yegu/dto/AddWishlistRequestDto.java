package kr.hanghae99.yegu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddWishlistRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
}