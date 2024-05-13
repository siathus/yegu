package kr.hanghae99.yegu.domain;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.checkout.Checkout;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CheckoutProduct {

    @Id @GeneratedValue
    @Column(name = "checkout_product_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkout_id")
    private Checkout checkout;

    @Setter
    @Column(name = "product_id")
    private Long productId;

    @Setter
    @Column
    private int price;

    @Setter
    @Column
    private int quantity;

    public static CheckoutProduct createCheckoutProduct(Long productId, int price, int quantity) {
        CheckoutProduct checkoutProduct = new CheckoutProduct();
        checkoutProduct.setProductId(productId);
        checkoutProduct.setPrice(price);
        checkoutProduct.setQuantity(quantity);
        return checkoutProduct;
    }
}
