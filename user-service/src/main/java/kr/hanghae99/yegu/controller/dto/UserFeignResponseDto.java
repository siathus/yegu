package kr.hanghae99.yegu.controller.dto;

import kr.hanghae99.yegu.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserFeignResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String postalCode;
    private String addressStreet;
    private String addressDetail;

    public UserFeignResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.postalCode = user.getPostalCode();
        this.addressStreet = user.getAddressStreet();
        this.addressDetail = user.getAddressDetail();
    }
}
