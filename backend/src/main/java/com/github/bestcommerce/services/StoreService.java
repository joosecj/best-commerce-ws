package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.StoreCategoryDTO;
import com.github.bestcommerce.dtos.v1.StoreCategoryProductOwerDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.entities.Store;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.repositories.PermissionRepository;
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
public class StoreService {
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final PermissionRepository permissionRepository;
    private static final String ERROR_NOT_FOUND_STORE = "Store not found";

    public StoreService(StoreRepository storeRepository, CategoryRepository categoryRepository, UserService userService, PermissionRepository permissionRepository) {
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.permissionRepository = permissionRepository;
    }

    @Transactional(readOnly = true)
    public StoreCategoryProductOwerDTO findById(UUID id) {
        Store storeEntity = storeRepository.findById(id).orElseThrow(
                StoreService::resourceNotFoundException);
        return new StoreCategoryProductOwerDTO(storeEntity);
    }

    @Transactional(readOnly = true)
    public Page<StoreCategoryDTO> findAll(Pageable pageable) {
        Page<Store> stores = storeRepository.findAll(pageable);
        return stores.map(StoreCategoryDTO::new);
    }

    @Transactional
    public StoreCategoryProductOwerDTO insert(StoreCategoryDTO storeCategoryDTO) {
        try {
            var storeEntity = copyDtoToEntity(storeCategoryDTO);
            return new StoreCategoryProductOwerDTO(storeRepository.save(grantAdminPermission(storeEntity)));
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        }
    }

    @Transactional
    public StoreCategoryDTO update(UUID id, StoreCategoryDTO storeCategoryDTO) {
        try {
            var storeEntity = storeRepository.getReferenceById(id);
            copyDtoToEntity(storeCategoryDTO);
            return new StoreCategoryDTO(storeRepository.save(storeEntity));
        } catch (EntityNotFoundException e) {
            throw resourceNotFoundException();
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(UUID id) {
        try {
            storeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw resourceNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Referential integrity failure");
        }
    }

    private Store copyDtoToEntity(StoreCategoryDTO storeCategoryDTO) {
        var name = storeCategoryDTO.getName();
        Category categoryStore = getCategory(storeCategoryDTO);
        var userEntity = userService.authenticated();
        if (userEntity == null) {
            throw new ResourceNotFoundException("user not logged in");
        }
        return new Store(
                formatTextToUppercaseAndTrim(name),
                categoryStore,
                userEntity
        );
    }

    private Category getCategory(StoreCategoryDTO storeCategoryDTO) {
        UUID categoryId = storeCategoryDTO.getCategory().getId();
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    private Store grantAdminPermission(Store storeEntity) {
        var idAdmin = UUID.fromString("8ddb099e-e17d-4dbb-9fc5-917b3b5f3640");
        var permissionEntity = permissionRepository.findById(idAdmin).orElseThrow(
                () -> new ResourceNotFoundException("Permission not found"));
        storeEntity.getClient().getPermissions().add(permissionEntity);
        return storeEntity;
    }

    private static String formatTextToUppercaseAndTrim(String text) {
        return text.toUpperCase().trim();
    }

    private static ResourceNotFoundException resourceNotFoundException() {
        return new ResourceNotFoundException(ERROR_NOT_FOUND_STORE);
    }

}
