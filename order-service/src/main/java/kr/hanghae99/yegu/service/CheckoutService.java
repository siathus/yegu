package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.CheckoutProduct;
import kr.hanghae99.yegu.domain.checkout.Checkout;
import kr.hanghae99.yegu.dto.DirectCheckoutRequestDto;
import kr.hanghae99.yegu.repository.CheckoutRepository;
import kr.hanghae99.yegu.service.client.ProductFeignClient;
import kr.hanghae99.yegu.service.client.dto.ProductFeignResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;

    private final ProductFeignClient productFeignClient;

    @Transactional
    public Long directSaveCheckout(DirectCheckoutRequestDto requestDto) {
        ProductFeignResponseDto product = productFeignClient.findByProductId(requestDto.getProductId());

        CheckoutProduct checkoutProduct = CheckoutProduct
                .createCheckoutProduct(product.getProductId(), product.getPrice(), requestDto.getQuantity());

        Checkout checkout = Checkout
                .createCheckout(requestDto.getUserId(), List.of(checkoutProduct));

        return checkoutRepository.save(checkout).getId();
    }
}
