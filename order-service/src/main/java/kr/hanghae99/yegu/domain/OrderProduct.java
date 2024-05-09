package kr.hanghae99.yegu.domain;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct {

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private Long productId;

    @Column
    private int quantity;

    @Builder
    public OrderProduct(Order order, Long productId, int quantity) {
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void cancelOrder() {
        // TODO: 주문 취소 로직 구현
//        product.restoreStock(quantity);
    }

    public void refundOrder() {
        // TODO: 반품 로직 구현
//        product.restoreStock(quantity);
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
