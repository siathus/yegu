package kr.hanghae99.yegu.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wishlist")
@RestController
public class WishlistController {

    private final WishlistService wishlistService;

    // TODO: 로그인 기능 구현 후 PathVariable 대신 유저 인증 정보로 userId 가져오기
    @GetMapping("/{userId}")
    public WishlistResponseDto getWishlist(@PathVariable Long userId, HttpServletRequest request) {
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return wishlistService.getWishlist(userId, host);
    }

    @PostMapping("")
    public SuccessResponseDto addProductToWishlist(@RequestBody AddWishlistRequestDto requestDto) {
        wishlistService.addProductToWishlist(requestDto);
        return new SuccessResponseDto(true);
    }
}
