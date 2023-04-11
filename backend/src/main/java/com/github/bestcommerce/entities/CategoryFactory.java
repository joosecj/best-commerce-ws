package com.github.bestcommerce.entities;

public class CategoryFactory {
    public static Category createCategory(CategoryType type) {
        return type.equals(CategoryType.PRODUCT) ? new CategoryProduct() : new CategoryStore();
    }
}
