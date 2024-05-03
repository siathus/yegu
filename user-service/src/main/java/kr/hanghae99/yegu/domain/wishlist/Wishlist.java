package kr.hanghae99.yegu.domain.wishlist;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.WishlistProduct;
import kr.hanghae99.yegu.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Wishlist {
    @Id @GeneratedValue
    @Column(name = "wishlist_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private List<WishlistProduct> wishlistProducts = new ArrayList<>();

    @Builder
    public Wishlist(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
}
