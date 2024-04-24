package kr.hanghae99.yegu.domain.wishlist;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.WishlistProduct;
import kr.hanghae99.yegu.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {
    @Id @GeneratedValue
    @Column(name = "wishlist_id")
    private Long id;

    @OneToOne(mappedBy = "wishlist")
    private User user;

    @OneToMany(mappedBy = "wishlist")
    private List<WishlistProduct> wishlistProducts = new ArrayList<>();
}
