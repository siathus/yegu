package kr.hanghae99.yegu.domain.product;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.WishlistProduct;
import kr.hanghae99.yegu.domain.category.Category;
import kr.hanghae99.yegu.domain.user.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Column(columnDefinition = "text")
    private String description;

    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<WishlistProduct> wishlistProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Builder
    public Product(String name, int price, int stock, String description, String thumbnailUrl, Category category, ProductStatus productStatus) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.category = category;
        this.status = productStatus;
    }
}
