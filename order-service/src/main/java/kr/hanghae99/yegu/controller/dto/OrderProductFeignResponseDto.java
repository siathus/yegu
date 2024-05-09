package kr.hanghae99.yegu.controller.dto;

import kr.hanghae99.yegu.service.client.dto.ProductFeignResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class OrderProductFeignResponseDto {
    private Long productId;
    private String name;
    private int price;
    private int quantity;

    public OrderProductFeignResponseDto(ProductFeignResponseDto dto, int quantity) {
        this.productId = dto.getProductId();
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.quantity = quantity;
    }
}
