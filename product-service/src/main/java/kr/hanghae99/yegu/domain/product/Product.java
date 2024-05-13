package kr.hanghae99.yegu.domain.product;

import jakarta.persistence.*;
import kr.hanghae99.yegu.domain.BaseTimeEntity;
import kr.hanghae99.yegu.domain.category.Category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private boolean isPreorder;

    @Column(nullable = true)
    private LocalDateTime preorderStartTime;

    @Column(nullable = true)
    private LocalDateTime preorderEndTime;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Builder
    public Product(String name, int price, int stock, String description, String thumbnailUrl,
                   Category category, boolean isPreorder, LocalDateTime preorderStartTime, LocalDateTime preorderEndTime,
                   ProductStatus productStatus) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.category = category;
        this.isPreorder = isPreorder;
        this.preorderStartTime = preorderStartTime;
        this.preorderEndTime = preorderEndTime;
        this.status = productStatus;
    }

    public void sell(int quantity) {
        this.stock -= quantity;
    }

    public void restoreStock(int quantity) {
        this.stock += quantity;
    }
}
