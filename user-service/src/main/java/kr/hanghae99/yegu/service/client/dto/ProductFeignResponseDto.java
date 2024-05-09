package kr.hanghae99.yegu.service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ProductFeignResponseDto {
    private Long productId;
    private String name;
    private int price;
    private String thumbnailUrl;
    private String categoryName;
}
