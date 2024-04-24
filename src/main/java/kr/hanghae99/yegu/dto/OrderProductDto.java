package kr.hanghae99.yegu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderProductDto {
    @NotNull
    private Long productId;

    private int price;
    @Min(1)
    private int quantity;
}
