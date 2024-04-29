package kr.hanghae99.yegu.domain;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WishlistProduct {
    @Id @GeneratedValue
    @Column(name = "wishlist_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Builder
    public WishlistProduct(Wishlist wishlist, Product product, int quantity) {
        this.wishlist = wishlist;
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
    }
}
