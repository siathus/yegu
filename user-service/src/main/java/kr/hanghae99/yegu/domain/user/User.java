package kr.hanghae99.yegu.domain.user;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.BaseTimeEntity;
import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
public class User extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 250)
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

    @OneToOne(mappedBy = "user")
    private Wishlist wishlist;

    @ColumnDefault("false")
    private Boolean withdraw;

    private LocalDateTime withdrawedAt;

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
