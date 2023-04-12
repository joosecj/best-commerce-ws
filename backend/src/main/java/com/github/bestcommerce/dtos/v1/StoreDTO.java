package com.github.bestcommerce.dtos.v1;

import com.github.bestcommerce.entities.Store;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class StoreDTO {
    private UUID id;
    @NotBlank(message = "required field")
    @Size(min = 3, max = 20)
    private String name;
    private CategoryDTO category;

    public StoreDTO() {
    }

    public StoreDTO(UUID id, String name, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public StoreDTO(Store storeEntity) {
        id = storeEntity.getId();
        name = storeEntity.getName();
        category = new CategoryDTO(storeEntity.getCategoryStore());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryDTO getCategory() {
        return category;
    }
}
