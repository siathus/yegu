package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.user.User;
import kr.hanghae99.yegu.dto.UserChangePasswordRequestDto;
import kr.hanghae99.yegu.dto.UserResponseDto;
import kr.hanghae99.yegu.dto.UserUpdateInfoRequestDto;
import kr.hanghae99.yegu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final EncryptionService encryptionService;
    private final WishlistService wishlistService;
    private final UserRepository userRepository;

    @Transactional
    public Long signup(User user) {
        User savedUser = userRepository.save(user);
        wishlistService.save(savedUser);
        return savedUser.getId();
    }

    @Transactional
    public void updateInfo(UserUpdateInfoRequestDto dto) {
        User user = userRepository
                .findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException());
        String encryptedPostalCode = encryptionService.encryptString(dto.getPostalCode());
        String encryptedAddressStreet = encryptionService.encryptString(dto.getAddressStreet());
        String encryptedAddressDetail = encryptionService.encryptString(dto.getAddressDetail());
        String encryptedPhone = encryptionService.encryptString(dto.getPhone());

        user.updateInfo(encryptedPostalCode, encryptedAddressStreet, encryptedAddressDetail, encryptedPhone);
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return new UserResponseDto(user);
    }

    @Transactional
    public void changePassword(UserChangePasswordRequestDto dto) {
        User user = userRepository
                .findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException());

        String encodedOldPassword = passwordEncoder.encode(dto.getOldPassword());
        if (passwordEncoder.matches(encodedOldPassword, user.getPassword())) {
            throw new RuntimeException("기존 비밀번호와 동일한 비밀번호를 사용할 수 없습니다.");
        }

        String encodedNewPassword = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(encodedNewPassword);
    }
}
