package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.StoreDTO;
import com.github.bestcommerce.entities.Store;
import com.github.bestcommerce.repositories.CategoryRepository;
import com.github.bestcommerce.repositories.StoreRepository;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    public StoreService(StoreRepository storeRepository, CategoryRepository categoryRepository) {
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<StoreDTO> findAll(Pageable pageable) {
        Page<Store> stores = storeRepository.findAll(pageable);
        return stores.map(StoreDTO::new);
    }

    @Transactional
    public StoreDTO insert(StoreDTO storeDTO) {
        try {
            UUID categoryId = storeDTO.getCategory().getId();
            var categoryStore = categoryRepository.findById(categoryId).orElseThrow(()
                    -> new ResourceNotFoundException("Category not found"));
            var storeEntitny = new Store();
            copyDtoToEntity(storeDTO, storeEntitny);
            storeEntitny.setCategoryStore(categoryStore);
            storeEntitny = storeRepository.save(storeEntitny);
            return new StoreDTO(storeEntitny);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        }
    }

    @Transactional
    public StoreDTO update(UUID id, StoreDTO storeDTO) {
        try {
            var storeEntity = storeRepository.getReferenceById(id);
            copyDtoToEntity(storeDTO, storeEntity);
            storeEntity = storeRepository.save(storeEntity);
            return new StoreDTO(storeEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Store not found");
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("Error");
        }
    }


    private void copyDtoToEntity(StoreDTO storeDTO, Store store) {
        store.setName(formatDataToSave(storeDTO.getName()));
    }


    private static String formatDataToSave(String text) {
        return text.toUpperCase().trim();
    }

}
