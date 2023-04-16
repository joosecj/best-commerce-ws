package com.github.bestcommerce.tests;

import com.github.bestcommerce.dtos.v1.CategoryDTO;
import com.github.bestcommerce.dtos.v1.StoreCategoryDTO;
import com.github.bestcommerce.entities.*;

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

    public static User createUser() {
        var user = new User();

        return user;
    }

    public static Store createStore(String type) {
        typeCategory = type;
        var typeCategory = createCategory(type);
        return new Store("NOVO TESTE", createCategory(type)
                , createUser());
    }

    public static StoreCategoryDTO createStoreDTO(){
        Store store = createStore(typeCategory);
        return new StoreCategoryDTO(store);
    }
}
