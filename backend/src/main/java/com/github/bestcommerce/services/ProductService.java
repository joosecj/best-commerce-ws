package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.dtos.v1.ProductCategoriesStoreDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.entities.Product;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.repositories.ProductRepository;
import com.github.bestcommerce.repositories.StoreRepository;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    private static final String ERROR_NOT_FOUND_STORE = "Store not found";

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.storeRepository = storeRepository;
    }

    @Transactional
    public ProductCategoriesStoreDTO insert(ProductCategoriesStoreDTO productCategoriesStoreDTO) {
        try {
            UUID storeId = productCategoriesStoreDTO.getStore().getId();
            var store = storeRepository.findById(storeId)
                    .orElseThrow(ProductService::resourceNotFoundException);
            var productEntity = new Product();
            copyDtoToEntity(productCategoriesStoreDTO, productEntity);
            productEntity.setStore(store);
            productEntity = productRepository.save(productEntity);
            return new ProductCategoriesStoreDTO(productEntity);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        }
    }

    private void copyDtoToEntity(ProductCategoriesStoreDTO productCategoriesStoreDTO, Product product) {
        product.setName(productCategoriesStoreDTO.getName());
        product.setDescription(productCategoriesStoreDTO.getDescription());
        product.setPrice(productCategoriesStoreDTO.getPrice());
        product.setImgUrl(productCategoriesStoreDTO.getImgUrl());
        product.getCategories().clear();
        for (CategoryDTO catDTO : productCategoriesStoreDTO.getCategories()) {
            var categoryId = catDTO.getId();
            Category categoryEntity = categoryRepository.findById(categoryId)
                    .orElseThrow(ProductService::resourceNotFoundException);
            product.getCategories().add(categoryEntity);
        }
    }

    private static ResourceNotFoundException resourceNotFoundException() {
        return new ResourceNotFoundException(ERROR_NOT_FOUND_STORE);
    }
}
