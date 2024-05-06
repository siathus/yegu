package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.controller.dto.OrderFeignResponseDto;
import kr.hanghae99.yegu.dto.OrderResponseDto;
import kr.hanghae99.yegu.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/internal/orders")
@RestController
public class OrderInternalApiController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public List<OrderFeignResponseDto> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getAllOrdersByUserId(userId);
    }
}
