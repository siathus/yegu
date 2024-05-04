package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/internal/users")
@RestController
public class UserInternalApiController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> orders() {
        
    }
}
