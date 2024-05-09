package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.wishlist.WishlistProduct;
import kr.hanghae99.yegu.domain.user.User;
import kr.hanghae99.yegu.domain.wishlist.Wishlist;
import kr.hanghae99.yegu.dto.AddWishlistRequestDto;
import kr.hanghae99.yegu.service.client.dto.ProductFeignResponseDto;
import kr.hanghae99.yegu.dto.WishlistProductResponseDto;
import kr.hanghae99.yegu.dto.WishlistResponseDto;
import kr.hanghae99.yegu.repository.WishlistRepository;
import kr.hanghae99.yegu.service.client.ProductFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;

    private ProductFeignClient productFeignClient;

    public Wishlist findByUserId(Long userId) {
        return wishlistRepository
                .findByUserId(userId);
    }

    public WishlistResponseDto getWishlist(Long userId, String host) {
        Wishlist wishlist = findByUserId(userId);
        List<WishlistProductResponseDto> wishlistProductDtos = new ArrayList<>();
        List<ProductFeignResponseDto> productFeignResponseDtos = productFeignClient.findAllByProductId(wishlist.getWishlistProducts()
                .stream()
                .map(w -> w.getProductId())
                .toList());
        Map<Long, Integer> productIdQuantityMap = wishlist.getWishlistProducts()
                .stream()
                .collect(Collectors.toMap(WishlistProduct::getProductId, WishlistProduct::getQuantity));
        for (ProductFeignResponseDto responseDto : productFeignResponseDtos) {
            WishlistProductResponseDto dto = WishlistProductResponseDto.builder()
                    .productId(responseDto.getProductId())
                    .name(responseDto.getName())
                    .price(responseDto.getPrice())
                    .thumbnailUrl(responseDto.getThumbnailUrl())
                    .host(host)
                    .quantity(productIdQuantityMap.get(responseDto.getProductId()))
                    .build();
            wishlistProductDtos.add(dto);
        }
        return WishlistResponseDto.builder()
                .userId(userId)
                .products(wishlistProductDtos)
                .build();
    }

    @Transactional
    public void addProductToWishlist(AddWishlistRequestDto requestDto) {
        Wishlist wishlist = wishlistRepository.findByUserId(requestDto.getUserId());
        List<WishlistProduct> wishlistProducts = wishlist.getWishlistProducts();
        Optional<WishlistProduct> alreadyContained = wishlistProducts.stream()
                .filter(wp -> wp.getProductId() == requestDto.getProductId()).findFirst();
        if (alreadyContained.isPresent()) {
            alreadyContained.get().addQuantity(requestDto.getQuantity());
        } else {
            wishlistProducts.add(WishlistProduct.builder()
                    .wishlist(wishlist)
                    .productId(requestDto.getProductId())
                    .quantity(requestDto.getQuantity())
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
