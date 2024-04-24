package kr.hanghae99.yegu.domain;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.wishlist.Wishlist;

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
}
