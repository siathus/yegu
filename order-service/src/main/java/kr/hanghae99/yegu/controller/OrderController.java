package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.dto.OrderResponseDto;
import kr.hanghae99.yegu.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    // TODO: PathVariable 대신 User 인증 정보로 userId 가져오기
    @GetMapping("/list/{userId}")
    public List<OrderResponseDto> getOrderList(@PathVariable Long userId) {
        return orderService.findAllByUserId(userId);
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refund/{orderId}")
    public ResponseEntity<String> requestRefundOrder(@PathVariable Long orderId) {
        orderService.requestReturn(orderId);
        return ResponseEntity.ok().build();
    }
}
