package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.entities.CategoryFactory;
import com.github.bestcommerce.entities.CategoryType;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.services.exceptions.DataBaseException;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
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
        try {
            var categoryType = handleTypeCategory(categoryDTO.getType());
            Category categoryEntity = CategoryFactory.createCategory(categoryType);
            copyDtoToEntity(categoryDTO, categoryEntity);
            categoryEntity = categoryRepository.save(categoryEntity);
            return new CategoryDTO(categoryEntity);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Type not registered");
        }
    }

    @Transactional
    public CategoryDTO update(UUID id, CategoryDTO categoryDTO) {
        try {
            var categoryEntity = categoryRepository.getReferenceById(id);
            copyDtoToEntity(categoryDTO, categoryEntity);
            categoryEntity = categoryRepository.save(categoryEntity);
            return new CategoryDTO(categoryEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Category not found");
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
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

    private void copyDtoToEntity(CategoryDTO categoryDTO, Category category) {
        category.setName(formatDataToSave(categoryDTO.getName()));
        category.setDescription(categoryDTO.getDescription());
        category.setType(handleTypeCategory(formatDataToSave(categoryDTO.getType())));
    }

    private static CategoryType handleTypeCategory(String text) {
        var type = formatDataToSave(text);
        return CategoryType.valueOf(type);
    }

    private static String formatDataToSave(String text) {
        return text.toUpperCase().trim();
    }

}
