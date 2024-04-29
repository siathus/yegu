package kr.hanghae99.yegu.domain.order;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.OrderProduct;
import kr.hanghae99.yegu.domain.user.entity.BaseTimeEntity;
import kr.hanghae99.yegu.domain.user.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(nullable = false)
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime deliveredAt;

    public void addOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
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
    public Order(User user, OrderStatus status) {
        this.user = user;
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

    public OrderStatus getStatus() {
        return status;
    }
}
