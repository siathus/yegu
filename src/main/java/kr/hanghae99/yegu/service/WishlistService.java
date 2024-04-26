package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import kr.hanghae99.yegu.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public Wishlist findByUserId(Long userId) {
        return wishlistRepository
                .findByUserId(userId);
    }

    public Long save() {
        return wishlistRepository.save(new Wishlist()).getId();
    }
}
