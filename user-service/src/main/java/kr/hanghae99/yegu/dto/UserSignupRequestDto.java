package kr.hanghae99.yegu.dto;

import jakarta.validation.constraints.Email;
import kr.hanghae99.yegu.domain.user.User;
import kr.hanghae99.yegu.service.EncryptionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter
public class UserSignupRequestDto {

    private String name;

    @Email
    private String email;
    private String password;
    private String phone;
    private String postalCode;
    private String addressStreet;
    private String addressDetail;

    public User toEntity(PasswordEncoder passwordEncoder, EncryptionService encryptionService) {
        return User.builder()
                .name(encryptionService.encryptString(name))
                .email(encryptionService.encryptString(email))
                .phone(phone)
                .postalCode(encryptionService.encryptString(postalCode))
                .addressStreet(passwordEncoder.encode(addressStreet))
                .addressDetail(passwordEncoder.encode(addressStreet))
                .password(passwordEncoder.encode(password))
                .build();
    }
}
