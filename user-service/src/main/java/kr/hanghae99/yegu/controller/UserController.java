package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.dto.UserChangePasswordRequestDto;
import kr.hanghae99.yegu.dto.UserSignupRequestDto;
import kr.hanghae99.yegu.dto.UserUpdateInfoRequestDto;
import kr.hanghae99.yegu.service.EncryptionService;
import kr.hanghae99.yegu.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("/users")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final EncryptionService encryptionService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto.toEntity(passwordEncoder, encryptionService));
        return ResponseEntity.ok("회원가입 완료!");
    }

    @PutMapping("/info")
    public SuccessResponseDto updateInfo(@RequestBody UserUpdateInfoRequestDto updateInfoRequestDto) {
        userService.updateInfo(updateInfoRequestDto);
        return new SuccessResponseDto(true);
    }

    @PutMapping("/password")
    public SuccessResponseDto changePassword(@RequestBody UserChangePasswordRequestDto passwordRequestDto) {
        userService.changePassword(passwordRequestDto);
        return new SuccessResponseDto(true);
    }
}
