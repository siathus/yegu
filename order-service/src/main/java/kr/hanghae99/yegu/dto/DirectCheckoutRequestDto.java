package kr.hanghae99.yegu.dto;

import lombok.Getter;

@Getter
public class DirectCheckoutRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
}
