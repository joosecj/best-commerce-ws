package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.dtos.v1.ProductCategoriesStoreDTO;
import com.github.bestcommerce.dtos.v1.ProductMinDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.entities.Product;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.repositories.ProductRepository;
import com.github.bestcommerce.repositories.StoreRepository;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    private static final String NOT_FOUND_STORE_ERROR_MESSAGE = "Store not found";
    private static final String NOT_FOUND_PRODUCT_ERROR_MESSAGE = "Product not found";
    private static final String NOT_FOUND_CATEGORY_ERROR_MESSAGE = "Category not found";

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          StoreRepository storeRepository, UserService userService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.storeRepository = storeRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public ProductCategoriesStoreDTO findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(NOT_FOUND_PRODUCT_ERROR_MESSAGE));
        return new ProductCategoriesStoreDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAllSearchName(String name, Pageable pageable) {
        Page<Product> productListResult = productRepository.searchByName(name, pageable);
        return productListResult.map(ProductMinDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable) {
        Page<Product> productListResult = productRepository.findAll(pageable);
        return productListResult.map(ProductMinDTO::new);
    }

    @Transactional
    public ProductCategoriesStoreDTO insert(ProductCategoriesStoreDTO productCategoriesStoreDTO) {
        try {
            var productEntity = new Product();
            return new ProductCategoriesStoreDTO(productRepository.save(
                    copyDtoToEntity(
                            productCategoriesStoreDTO, productEntity
                    )));
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        }
    }

    @Transactional
    public ProductCategoriesStoreDTO update(UUID id, ProductCategoriesStoreDTO productCategoriesStoreDTO) {
        try {
            var productEntity = productRepository.getReferenceById(id);
            return new ProductCategoriesStoreDTO(
                    productRepository.save(copyDtoToEntity(productCategoriesStoreDTO, productEntity)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(NOT_FOUND_PRODUCT_ERROR_MESSAGE);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(NOT_FOUND_PRODUCT_ERROR_MESSAGE);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Referential integrity failure");
        }
    }

    private Product copyDtoToEntity(ProductCategoriesStoreDTO productCategoriesStoreDTO, Product product) {
        var userEntity = userService.authenticated();
        UUID storeId = userEntity.getStore().getId();
        var storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_STORE_ERROR_MESSAGE));
        product.setName(productCategoriesStoreDTO.getName());
        product.setDescription(productCategoriesStoreDTO.getDescription());
        product.setPrice(productCategoriesStoreDTO.getPrice());
        product.setImgUrl(productCategoriesStoreDTO.getImgUrl());
        product.getCategories().clear();
        for (CategoryDTO catDTO : productCategoriesStoreDTO.getCategories()) {
            var categoryId = catDTO.getId();
            Category categoryProduct = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_CATEGORY_ERROR_MESSAGE));
            product.getCategories().add(categoryProduct);
        }
        product.setStore(storeEntity);
        return product;
    }

}
