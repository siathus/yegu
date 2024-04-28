package kr.hanghae99.yegu.domain.user.entity;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.order.Order;
import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
public class User extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String addressStreet;

    private String addressDetail;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @ColumnDefault("false")
    private Boolean withdraw;

    private LocalDateTime withdrawedAt;

    public void addWishlist(Long wishlistId) {
        this.wishlist = Wishlist.builder()
                .id(wishlistId)
                .build();
    }

    public void updateInfo(String postalCode, String addressStreet, String addressDetail, String phone) {
        this.postalCode = postalCode;
        this.addressStreet = addressStreet;
        this.addressDetail = addressDetail;
        this.phone = phone;
    }

    @Builder
    public User(String email, String password, String name, String phone, String postalCode, String addressStreet, String addressDetail) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.postalCode = postalCode;
        this.addressStreet = addressStreet;
        this.addressDetail = addressDetail;
    }

    public Long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
