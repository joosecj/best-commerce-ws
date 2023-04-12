package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.StoreDTO;
import com.github.bestcommerce.entities.Store;
import com.github.bestcommerce.repositories.StoreRepository;
import com.github.bestcommerce.services.exceptions.ResourceNotFoundException;
import com.github.bestcommerce.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StoreServiceTests {

    @InjectMocks
    private StoreService storeService;

    @Mock
    private StoreRepository storeRepository;
    private UUID existingId;
    private UUID nonExistingId;
    private Store store;
    private String typeCategory;
    @BeforeEach
    void setUp() {
        existingId = UUID.fromString("046acaaf-1be6-44b4-b12e-43f51753823e");
        nonExistingId = UUID.randomUUID();
        typeCategory = "STORE";
        store = Factory.createStore(typeCategory);

        when(storeRepository.findById(existingId)).thenReturn(Optional.of(store));
        when(storeRepository.findById(nonExistingId)).thenReturn(Optional.empty());
    }

    @Test
    void findByIdShouldReturnStoreDTOWhenIdExists() {
        StoreDTO result = storeService.findById(existingId);
        Assertions.assertNotNull(result);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            storeService.findById(nonExistingId);
        });
    }
}
