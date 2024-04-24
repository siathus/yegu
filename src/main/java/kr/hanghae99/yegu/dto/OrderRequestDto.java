package kr.hanghae99.yegu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class OrderRequestDto {
    @NotNull
    private Long userId;
    private List<OrderProductDto> products = new ArrayList<>();
}
