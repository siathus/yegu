package kr.hanghae99.yegu.domain.checkout;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.BaseTimeEntity;
import kr.hanghae99.yegu.domain.CheckoutProduct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Checkout extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "checkout_id")
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL)
    private List<CheckoutProduct> checkoutProducts = new ArrayList<>();

    public static Checkout createCheckout(Long userId, List<CheckoutProduct> checkoutProducts) {
        Checkout checkout = new Checkout();
        checkout.setUserId(userId);
        for (CheckoutProduct checkoutProduct : checkoutProducts) {
            checkout.addCheckoutProduct(checkoutProduct);
        }
        return checkout;
    }

    public void addCheckoutProduct(CheckoutProduct checkoutProduct) {
        checkoutProducts.add(checkoutProduct);
        checkoutProduct.setCheckout(this);
    }
}
