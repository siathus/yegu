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

    public Wishlist findById(Long id) {
        return wishlistRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 위시리스트를 찾을 수 없습니다. id = " + id));
    }

}
