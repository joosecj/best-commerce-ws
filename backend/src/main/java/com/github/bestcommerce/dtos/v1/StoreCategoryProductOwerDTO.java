package com.github.bestcommerce.dtos.v1;

import com.github.bestcommerce.entities.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StoreCategoryProductOwerDTO {
    private UUID id;
    private String name;
    private CategoryDTO category;
    private List<ProductMinDTO> products = new ArrayList<>();
    private UserMinDTO owner;

    public StoreCategoryProductOwerDTO() {
    }

    public StoreCategoryProductOwerDTO(UUID id, String name, CategoryDTO category, UserMinDTO client) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.owner = client;
    }

    public StoreCategoryProductOwerDTO(Store storeEntity) {
        id = storeEntity.getId();
        name = storeEntity.getName();
        category = new CategoryDTO(storeEntity.getCategoryStore());
        products = storeEntity.getProducts().stream().map(ProductMinDTO::new).toList();
        owner = new UserMinDTO(storeEntity.getClient());
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

    public List<ProductMinDTO> getProducts() {
        return products;
    }

    public UserMinDTO getOwner() {
        return owner;
    }
}
