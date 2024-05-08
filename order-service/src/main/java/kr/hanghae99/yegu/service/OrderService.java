package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.controller.dto.OrderFeignResponseDto;
import kr.hanghae99.yegu.controller.dto.OrderProductFeignResponseDto;
import kr.hanghae99.yegu.domain.OrderProduct;
import kr.hanghae99.yegu.domain.order.Order;
import kr.hanghae99.yegu.domain.order.OrderStatus;
import kr.hanghae99.yegu.dto.OrderProductDto;
import kr.hanghae99.yegu.dto.OrderRequestDto;
import kr.hanghae99.yegu.dto.OrderResponseDto;
import kr.hanghae99.yegu.repository.OrderRepository;
import kr.hanghae99.yegu.service.client.ProductFeignClient;
import kr.hanghae99.yegu.service.client.dto.ProductFeignResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductFeignClient productFeignClient;

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

    public List<OrderFeignResponseDto> getAllOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        List<OrderFeignResponseDto> orderFeignResponseDtos = new ArrayList<>();
        for (Order order : orders) {
            List<Long> productIds = order.getOrderProducts().stream()
                    .map(orderProduct -> orderProduct.getProductId())
                    .toList();

            List<ProductFeignResponseDto> productFeignResponseDtos = productFeignClient.findAllByProductIds(productIds);
            Map<Long, ProductFeignResponseDto> productIdMap = productFeignResponseDtos.stream()
                    .collect(Collectors.toMap(p -> p.getProductId(), p -> p));

            List<OrderProductFeignResponseDto> orderProductFeignResponseDtos = new ArrayList<>();
            order.getOrderProducts().stream()
                    .forEach(op -> orderProductFeignResponseDtos.add(
                            new OrderProductFeignResponseDto(
                                    productIdMap.get(op.getProductId()), op.getQuantity())));

            orderFeignResponseDtos.add(OrderFeignResponseDto.builder()
                    .orderId(order.getId())
                    .orderProducts(orderProductFeignResponseDtos)
                    .totalPrice(order.getTotalPrice())
                    .status(order.getStatus().name())
                    .deliveredAt(order.getDeliveredAt())
                    .build());
        }
        return orderFeignResponseDtos;
    }

    public List<Order> findAllByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByStatus(orderStatus);
    }

    public Order findById(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문을 조회할 수 없습니다. id = " + id));
    }

    @Transactional
    public Long purchaseProduct(OrderRequestDto requestDto) {
        List<ProductFeignResponseDto> products = productFeignClient.findAllByProductIds(requestDto.getProducts().stream()
                .map(OrderProductDto::getProductId)
                .toList());

        Order order = Order.builder()
                .userId(requestDto.getUserId())
                .status(OrderStatus.ORDERED)
                .build();

        List<OrderProduct> orderProducts = new ArrayList<>();
        int totalPrice = 0;
        for (ProductFeignResponseDto product : products) {
            for (OrderProductDto requestDtoProduct : requestDto.getProducts()) {
                if (requestDtoProduct.getProductId() == product.getProductId()) {
                    totalPrice += requestDtoProduct.getQuantity() * product.getPrice();
                    OrderProduct orderProduct = OrderProduct.builder()
                            .order(order)
                            .productId(product.getProductId())
                            .quantity(requestDtoProduct.getQuantity())
                            .build();
                    orderProducts.add(orderProduct);
                    // TODO: 상품 재고 감소
//                    product.sell(requestDtoProduct.getQuantity());
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
