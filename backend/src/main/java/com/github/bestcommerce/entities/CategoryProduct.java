package com.github.bestcommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_category_product")
public class CategoryProduct extends Category {
    @ManyToMany(mappedBy = "categories")
    @JsonManagedReference
    private final Set<Product> products = new HashSet<>();

    public CategoryProduct() {
    }

    public CategoryProduct(UUID id, String name, String description, CategoryType type) {
        super(id, name, description, type);
    }

    public Set<Product> getList() {
        return products;
    }

}
