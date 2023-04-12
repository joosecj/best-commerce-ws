package com.github.bestcommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
public class CategoryProduct extends Category{
    @ManyToMany(mappedBy = "categories")
    @JsonManagedReference
    private Set<Product> products = new HashSet<>();

    public CategoryProduct() {
    }

    public CategoryProduct(UUID id, String name, String description, CategoryType type) {
        super(id, name, description, type);
    }

    public Set<Product> getList() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryProduct that = (CategoryProduct) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
