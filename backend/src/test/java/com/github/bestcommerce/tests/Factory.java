package com.github.bestcommerce.tests;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.dtos.v1.StoreDTO;
import com.github.bestcommerce.entities.Category;
import com.github.bestcommerce.entities.CategoryFactory;
import com.github.bestcommerce.entities.CategoryType;
import com.github.bestcommerce.entities.Store;

import java.util.UUID;

public class Factory {
    static String typeCategory;
    public static Category createCategory(String type) {
        typeCategory = type;
        var categoryType = CategoryType.valueOf(type);

        Category category = CategoryFactory.createCategory(categoryType);
        category.setId(UUID.fromString("bfc46fb7-9b40-43fd-9e74-2fcf6f7218ab"));
        category.setName("TELEVISÃO");
        category.setDescription("Brasileirão - 2023");
        category.setType(categoryType);
        return category;
    }

    public static CategoryDTO createCategoryDTO(){
        Category category = createCategory(typeCategory);
        return new CategoryDTO(category);
    }

    public static CategoryDTO createPersonCustomized(String name, String descritpion,  CategoryType type) {
        Category category = CategoryFactory.createCategory(type);
        category.setId(UUID.fromString("bfc46fb7-9b40-43fd-9e74-2fcf6f7218ab"));
        category.setName(name);
        category.setDescription(descritpion);
        category.setType(type);
        return new CategoryDTO(category);
    }

    public static Store createStore(String type) {
        typeCategory = type;
        var typeCategory = createCategory(type);
        return new Store(UUID.fromString("c37cbb48-fd22-452c-bced-ee13b5ad776b"),
                "MEDICO", typeCategory);
    }

    public static StoreDTO createStoreDTO(){
        Store store = createStore(typeCategory);
        return new StoreDTO(store);
    }
}
