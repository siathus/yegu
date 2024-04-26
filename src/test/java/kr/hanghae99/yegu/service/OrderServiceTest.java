package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.category.Category;
import kr.hanghae99.yegu.domain.order.Order;
import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.product.ProductStatus;
import kr.hanghae99.yegu.domain.user.entity.User;
import kr.hanghae99.yegu.dto.OrderProductDto;
import kr.hanghae99.yegu.dto.OrderRequestDto;
import kr.hanghae99.yegu.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("unit")
@Transactional
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("[SUCCESS] 상품 주문 완료")
    public void purchase_order_success() {
        // given
        User user = User.builder()
                .name("홍길동")
                .email("gildong@gildong.com")
                .password("gildong")
                .phone("010-1234-5678")
                .postalCode("04524")
                .addressStreet("서울특별시 중구 세종대로 110")
                .addressDetail("서울특별시청")
                .build();
        Long userId = userService.signup(user);

        Category category = new Category("카테고리1");

        categoryService.save(category);

        Product product1 = Product.builder()
                .name("상품1")
                .price(10000)
                .stock(5)
                .description("상품1설명")
                .thumbnailUrl(null)
                .category(category)
                .productStatus(ProductStatus.AVAILABLE)
                .build();
        Product product2 = Product.builder()
                .name("상품2")
                .price(1000)
                .stock(5)
                .description("상품2설명")
                .thumbnailUrl(null)
                .category(category)
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        long product1id = productService.save(product1);
        long product2id = productService.save(product2);

        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(userId);
        OrderProductDto orderProductDto1 = new OrderProductDto(product1id, 10000, 3);
        OrderProductDto orderProductDto2 = new OrderProductDto(product2id, 1000, 2);
        orderRequestDto.setProducts(List.of(orderProductDto1, orderProductDto2));

        // when
        Long orderId = orderService.purchaseProduct(orderRequestDto);

        // given
        Order savedOrder = orderRepository.findById(orderId).get();
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getTotalPrice()).isEqualTo(32000);
    }
}