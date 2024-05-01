package kr.hanghae99.yegu.service;

import kr.hanghae99.yegu.domain.category.Category;
import kr.hanghae99.yegu.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(Category category) {
        return categoryRepository.save(category).getId();
    }
}
