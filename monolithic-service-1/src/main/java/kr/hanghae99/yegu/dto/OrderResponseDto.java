package kr.hanghae99.yegu.dto;

import kr.hanghae99.yegu.domain.order.OrderStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OrderResponseDto {
    private Long orderId;
    private int totalPrice;
    private OrderStatus status;

}
