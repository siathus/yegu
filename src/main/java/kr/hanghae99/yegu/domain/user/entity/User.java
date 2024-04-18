package kr.hanghae99.yegu.domain.user.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String name;

    private String phone;

    @Embedded
    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean withdraw;

    @Nullable
    private LocalDateTime withdrawedAt;
}
