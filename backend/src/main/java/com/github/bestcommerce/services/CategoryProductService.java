package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.entities.CategoryProduct;
import com.github.bestcommerce.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CategoryProductService {
    private final CategoryRepository categoryRepository;

    public CategoryProductService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(CategoryDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> searchByName(String name, Pageable pageable) {
        Page<Category> categories = categoryRepository.searchByName(name, pageable);
        return categories.map(CategoryDTO::new);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category categoryProduct = new CategoryProduct();
        categoryProduct.setName(categoryDTO.getName());
        categoryProduct.setDescription(categoryDTO.getDescription());
        categoryProduct = categoryRepository.save(categoryProduct);
        return new CategoryDTO(categoryProduct);
    }

    @Transactional
    public CategoryDTO update(UUID id, CategoryDTO categoryDTO) {
        var categoryEntity = categoryRepository.getReferenceById(id);
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setDescription(categoryDTO.getDescription());
        categoryEntity = categoryRepository.save(categoryEntity);
        return new CategoryDTO(categoryEntity);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }


}
