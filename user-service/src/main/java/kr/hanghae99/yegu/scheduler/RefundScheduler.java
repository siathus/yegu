package kr.hanghae99.yegu.scheduler;

import kr.hanghae99.yegu.domain.order.Order;
import kr.hanghae99.yegu.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RefundScheduler {

    private final OrderService orderService;

    @Scheduled(cron = "0 0/10 * * * *")
    public void startDeliveryProcess() {
        List<Order> orders = orderService.findAllByOrderStatus(OrderStatus.ORDERED);
        LocalDateTime checkTime = LocalDateTime.now().minusHours(24);
        for (Order order : orders) {
            if (order.getCreatedAt().isBefore(checkTime)) {
                order.startDelivery();
            }
        }
        orderService.saveAll(orders);
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void completeDeliveryProcess() {
        List<Order> orders = orderService.findAllByOrderStatus(OrderStatus.DELIVERY_START);
        LocalDateTime checkTime = LocalDateTime.now().minusHours(24);
        for (Order order : orders) {
            if (order.getUpdatedAt().isBefore(checkTime)) {
                order.completeDelivery();
            }
        }
        orderService.saveAll(orders);
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void completeReturnProcess() {
        List<Order> orders = orderService.findAllByOrderStatus(OrderStatus.RETURN_REQUESTED);
        LocalDateTime checkTime = LocalDateTime.now().minusHours(24);
        for (Order order : orders) {
            if (order.getDeliveredAt().isBefore(checkTime)) {
                order.completeReturn();
            }
        }
        orderService.saveAll(orders);
    }
}
