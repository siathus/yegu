package kr.hanghae99.yegu.controller;

import kr.hanghae99.yegu.dto.OrderRequestDto;
import kr.hanghae99.yegu.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/order")
public class OrderController {

    private final OrderService orderService;

}
