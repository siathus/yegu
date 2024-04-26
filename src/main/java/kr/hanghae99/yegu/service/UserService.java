package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.user.entity.User;
import kr.hanghae99.yegu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final WishlistService wishlistService;
    private final UserRepository userRepository;

    @Transactional
    public Long signup(User user) {
        User savedUser = userRepository.save(user);
        Long wishlistId = wishlistService.save();
        savedUser.addWishlist(wishlistId);
        return savedUser.getId();
    }

    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

}
