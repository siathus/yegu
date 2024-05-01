package kr.hanghae99.yegu.repository;

import kr.hanghae99.yegu.domain.order.Order;
import kr.hanghae99.yegu.domain.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
    List<Order> findAllByStatus(OrderStatus orderStatus);
}
