package kr.hanghae99.yegu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishlistResponseDto {
    private Long wishlistId;
    private List<WishlistProductResponseDto> products = new ArrayList<>();
}
