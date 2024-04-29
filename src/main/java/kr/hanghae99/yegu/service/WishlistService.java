package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.WishlistProduct;
import kr.hanghae99.yegu.domain.product.Product;
import kr.hanghae99.yegu.domain.user.entity.User;
import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import kr.hanghae99.yegu.dto.AddWishlistRequestDto;
import kr.hanghae99.yegu.dto.WishlistProductResponseDto;
import kr.hanghae99.yegu.dto.WishlistResponseDto;
import kr.hanghae99.yegu.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WishlistService {

    private final ProductService productService;
    private final WishlistRepository wishlistRepository;

    public Wishlist findByUserId(Long userId) {
        return wishlistRepository
                .findByUserId(userId);
    }

    public WishlistResponseDto getWishlist(Long userId, String host) {
        Wishlist wishlist = findByUserId(userId);
        List<WishlistProductResponseDto> productDtos = new ArrayList<>();
        for (WishlistProduct wishlistProduct : wishlist.getWishlistProducts()) {
            WishlistProductResponseDto dto = new WishlistProductResponseDto(wishlistProduct.getProduct(), host, wishlistProduct.getQuantity());
            productDtos.add(dto);
        }
        return new WishlistResponseDto(userId, productDtos);
    }

    @Transactional
    public void addProductToWishlist(AddWishlistRequestDto dto) {
        Wishlist wishlist = wishlistRepository.findByUserId(dto.getUserId());
        List<WishlistProduct> wishlistProducts = wishlist.getWishlistProducts();
        Optional<WishlistProduct> alreadyContained = wishlistProducts.stream()
                .filter(wp -> wp.getProduct().getId() == dto.getProductId()).findFirst();
        if (alreadyContained.isPresent()) {
            alreadyContained.get().addQuantity(dto.getQuantity());
        } else {
            Product product = productService.findById(dto.getProductId());
            wishlistProducts.add(WishlistProduct.builder()
                    .wishlist(wishlist)
                    .product(product)
                    .quantity(dto.getQuantity())
                    .build());
        }
    }

    @Transactional
    public void save(User user) {
        wishlistRepository.save(Wishlist.builder()
                .user(user)
                .build());
    }
}
