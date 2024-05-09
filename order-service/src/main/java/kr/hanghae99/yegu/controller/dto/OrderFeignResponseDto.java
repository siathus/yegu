package kr.hanghae99.yegu.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter @Setter
public class OrderFeignResponseDto {
    private Long orderId;
    private List<OrderProductFeignResponseDto> orderProducts;
    private int totalPrice;
    private String status;
    private LocalDateTime deliveredAt;
}
