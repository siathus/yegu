package kr.hanghae99.yegu.repository;

import kr.hanghae99.yegu.domain.checkout.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findByUserId(Long userId);
}
