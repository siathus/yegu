package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.controller.dto.UserFeignResponseDto;
import kr.hanghae99.yegu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/internal/users")
@RestController
public class UserInternalApiController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserFeignResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .ok(userService.getUserById(id));
    }
}
