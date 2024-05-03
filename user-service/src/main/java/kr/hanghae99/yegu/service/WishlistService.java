package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.user.User;
import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import kr.hanghae99.yegu.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;

    public Wishlist findByUserId(Long userId) {
        return wishlistRepository
                .findByUserId(userId);
    }

    @Transactional
    public void save(User user) {
        wishlistRepository.save(Wishlist.builder()
                .user(user)
                .build());
    }
}
