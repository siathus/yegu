package kr.hanghae99.yegu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserChangePasswordRequestDto {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
