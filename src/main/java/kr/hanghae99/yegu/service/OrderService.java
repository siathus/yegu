package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.OrderProduct;
import kr.hanghae99.yegu.domain.order.Order;
import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.user.entity.User;
import kr.hanghae99.yegu.dto.OrderProductDto;
import kr.hanghae99.yegu.dto.OrderRequestDto;
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

        List<OrderProduct> orderProducts = new ArrayList<>();
        int totalPrice = 0;
        for (Product product : products) {
            for (OrderProductDto requestDtoProduct : requestDto.getProducts()) {
                if (requestDtoProduct.getProductId() == product.getId()) {
                    totalPrice += requestDtoProduct.getQuantity() * requestDtoProduct.getPrice();
                    orderProducts.add(OrderProduct.builder()
                            .product(product)
                            .quantity(requestDtoProduct.getQuantity())
                            .build());
                    break;
                }
            }
        }
        Order order = Order.builder()
                .user(orderedUser)
                .orderProducts(orderProducts)
                .totalPrice(totalPrice)
                .build();
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }
}
