package com.github.bestcommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;
import java.util.UUID;
@Entity
public class CategoryProduct extends Category{
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

    public CategoryProduct() {
    }

    public CategoryProduct(UUID id, String name, String discription, CategoryType type, Set<Product> products) {
        super(id, name, discription, type);
        this.products = products;
    }

    public Set<Product> getList() {
        return products;
    }
}
