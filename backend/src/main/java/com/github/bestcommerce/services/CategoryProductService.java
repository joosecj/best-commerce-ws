package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.entities.CategoryProduct;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.services.exceptions.DataBaseException;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            var categoryEntity = categoryRepository.getReferenceById(id);
            categoryEntity.setName(categoryDTO.getName());
            categoryEntity.setDescription(categoryDTO.getDescription());
            categoryEntity = categoryRepository.save(categoryEntity);
            return new CategoryDTO(categoryEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Category not found");
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Category not found");
        } catch (DataIntegrityViolationException e ) {
            throw new DataBaseException("Referential integrity failure");
        }

    }


}
