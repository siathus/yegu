package kr.hanghae99.yegu.domain.order;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.BaseTimeEntity;
import kr.hanghae99.yegu.domain.OrderProduct;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "orders")
public class Order extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column
    private Long userId;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(nullable = false)
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private LocalDateTime deliveredAt;

    public void addOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void startDelivery() {
        this.status = OrderStatus.DELIVERY_START;
    }

    public void completeDelivery() {
        this.status = OrderStatus.DELIVERED;
        this.deliveredAt = LocalDateTime.now();
    }

    public void requestReturn() {
        LocalDateTime limitTime = LocalDateTime.now().minusHours(24);
        if (this.status != OrderStatus.DELIVERED || deliveredAt.isBefore(limitTime)) {
            throw new IllegalStateException("배송 완료된 후 24시간 전에만 반품 가능");
        }
        this.status = OrderStatus.RETURN_REQUESTED;
    }

    public void completeReturn() {
        this.status = OrderStatus.RETURN_COMPLETED;
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.refundOrder();
        }
    }

    public void cancelOrder() {
        if (deliveredAt == null || status != OrderStatus.ORDERED) {
            throw new IllegalStateException("취소가 불가능한 상태입니다");
        }
        this.status = OrderStatus.CANCELLED;
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.cancelOrder();
        }
    }

    @Builder
    public Order(Long userId, OrderStatus status) {
        this.userId = userId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }
}
