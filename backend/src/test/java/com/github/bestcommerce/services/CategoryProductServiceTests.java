package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CategoryProductServiceTests {
    @InjectMocks
    private CategoryProductService categoryProductService;

    @Mock
    private CategoryRepository categoryRepository;
    private UUID existingId;
    private UUID nonExistingId;
    private UUID dependentId;
    private String existingName;
    private String existingCategoryProductType;
    private String existingCategoryStoreType;
    private Category category;
    private CategoryDTO categoryDTO;
    private PageImpl<Category> page;

    @BeforeEach
    void setUp() throws Exception {
        existingCategoryProductType = "PRODUCT";
        category = Factory.createCategory(existingCategoryProductType);
        categoryDTO = Factory.createCategoryDTO();
        page = new PageImpl<>(List.of(category));

        when(categoryRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

    }

    @Test
    public void findAllPagedShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CategoryDTO> result = categoryProductService.findAll(pageable);

        Assertions.assertNotNull(result);
        Mockito.verify(categoryRepository, times(1)).findAll(pageable);
    }
}
