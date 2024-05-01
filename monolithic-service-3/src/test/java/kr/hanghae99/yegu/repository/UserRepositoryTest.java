package kr.hanghae99.yegu.repository;

import kr.hanghae99.yegu.domain.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("[SUCCESS] 회원가입 성공")
    void signup_success() {
        // given
        User user = User.builder()
                .name("홍길동")
                .email("gildong@gildong.com")
                .password("gildong")
                .phone("010-1234-5678")
                .postalCode("04524")
                .addressStreet("서울특별시 중구 세종대로 110")
                .addressDetail("서울특별시청")
                .build();

        // when
        User savedUser = userRepository.save(user);
        log.info(user.toString());

        // then
        Assertions.assertThat(userRepository.findById(savedUser.getId())).isPresent();
    }
}