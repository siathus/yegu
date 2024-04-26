package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import kr.hanghae99.yegu.dto.WishlistProductResponseDto;
import kr.hanghae99.yegu.dto.WishlistResponseDto;
import kr.hanghae99.yegu.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;


    // TODO: 로그인 기능 구현 후 PathVariable 대신 유저 인증 정보로 userId 가져오기
    @GetMapping("/{userId}")
    public WishlistResponseDto getWishlist(@PathVariable Long userId) {
        Wishlist wishlist = wishlistService.findByUserId(userId);
        
    }
}
