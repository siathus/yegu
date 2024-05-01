package kr.hanghae99.yegu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateInfoRequestDto {
    private Long id;
    private String postalCode;
    private String addressStreet;
    private String addressDetail;
    private String phone;
}
