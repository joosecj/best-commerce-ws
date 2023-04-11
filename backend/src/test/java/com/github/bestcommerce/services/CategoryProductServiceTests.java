package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.services.exceptions.DataBaseException;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import com.github.bestcommerce.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryProductServiceTests {
    @InjectMocks
    private CategoryProductService categoryProductService;

    @Mock
    private CategoryRepository categoryRepository;
    private UUID existingId;
    private UUID nonExistingId;
    private UUID dependentId;
    private String nonExistingCategory;
    private String existingCategoryNameProductType;
    private String existingCategorNameStoreType;
    private Category category;
    private CategoryDTO categoryDTO;
    private PageImpl<Category> page;

    @BeforeEach
    void setUp() {
        existingCategoryNameProductType = "PRODUCT";
        category = Factory.createCategory(existingCategoryNameProductType);
        categoryDTO = Factory.createCategoryDTO();
        existingId = UUID.fromString("046acaaf-1be6-44b4-b12e-43f51753823e");
        nonExistingId = UUID.randomUUID();
        dependentId = UUID.randomUUID();
        existingCategoryNameProductType = "TELEVISÃO";
        existingCategorNameStoreType = "TECNOLOGIA";
        page = new PageImpl<>(List.of(category));

        when(categoryRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);


        doNothing().when(categoryRepository).deleteById(existingId);
        doThrow(EmptyResultDataAccessException.class).when(categoryRepository).deleteById(nonExistingId);
        doThrow(DataIntegrityViolationException.class).when(categoryRepository).deleteById(dependentId);


    }

    @Test
    void findAllPagedShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CategoryDTO> result = categoryProductService.findAll(pageable);

        Assertions.assertNotNull(result);
        Mockito.verify(categoryRepository, times(1)).findAll(pageable);
    }

    @Test
    void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> categoryProductService.delete(existingId));
        Mockito.verify(categoryRepository, times(1)).deleteById(existingId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryProductService.delete(nonExistingId));
        Mockito.verify(categoryRepository, times(1)).deleteById(nonExistingId);
    }

    @Test
    void deleteShouldThrowDatabaseExceptionWhenDependedId() {
        Assertions.assertThrows(DataBaseException.class, () -> categoryProductService.delete(dependentId));
        verify(categoryRepository, times(1)).deleteById(dependentId);
    }

}
