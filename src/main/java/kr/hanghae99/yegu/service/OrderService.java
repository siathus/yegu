package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.OrderProduct;
import kr.hanghae99.yegu.domain.order.Order;
import kr.hanghae99.yegu.domain.order.OrderStatus;
import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.user.entity.User;
import kr.hanghae99.yegu.dto.OrderProductDto;
import kr.hanghae99.yegu.dto.OrderRequestDto;
import kr.hanghae99.yegu.dto.OrderResponseDto;
import kr.hanghae99.yegu.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final UserService userService;
    private final ProductService productService;

    private final OrderRepository orderRepository;

    public List<OrderResponseDto> findAllByUserId(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream()
                .map(order -> OrderResponseDto.builder()
                        .orderId(order.getId())
                        .totalPrice(order.getTotalPrice())
                        .status(order.getStatus())
                        .build())
                .toList();
    }

    public List<Order> findAllByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(orderStatus);
    }

    public Order findById(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문을 조회할 수 없습니다. id = " + id));
    }

    @Transactional
    public Long purchaseProduct(OrderRequestDto requestDto) {
        User orderedUser = userService.findById(requestDto.getUserId());
        List<Product> products = productService.findAllById(requestDto.getProducts().stream()
                .map(OrderProductDto::getProductId).toList());

        Order order = Order.builder()
                .user(orderedUser)
                .status(OrderStatus.ORDERED)
                .build();

        List<OrderProduct> orderProducts = new ArrayList<>();
        int totalPrice = 0;
        for (Product product : products) {
            for (OrderProductDto requestDtoProduct : requestDto.getProducts()) {
                if (requestDtoProduct.getProductId() == product.getId()) {
                    totalPrice += requestDtoProduct.getQuantity() * product.getPrice();
                    OrderProduct orderProduct = OrderProduct.builder()
                            .order(order)
                            .product(product)
                            .quantity(requestDtoProduct.getQuantity())
                            .build();
                    orderProducts.add(orderProduct);
                    product.sell(requestDtoProduct.getQuantity());
                    break;
                }
            }
        }
        order.setTotalPrice(totalPrice);
        order.addOrderProducts(orderProducts);

        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException());
        order.cancelOrder();
    }

    @Transactional
    public void requestReturn(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException());
        order.requestReturn();
    }

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void saveAll(List<Order> orders) {
        orderRepository.saveAll(orders);
    }
}
