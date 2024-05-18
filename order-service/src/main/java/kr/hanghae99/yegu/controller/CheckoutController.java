package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.dto.DirectCheckoutRequestDto;
import kr.hanghae99.yegu.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/checkout")
@RestController
public class CheckoutController {

    private final CheckoutService checkoutService;

    // TODO: 유저 인증 정보와 CheckoutRequestDto의 userId 검증하기
    @PostMapping("/direct")
    public Long directCheckout(@RequestBody DirectCheckoutRequestDto requestDto) {
        return checkoutService.directSaveCheckout(requestDto);
    }
}
