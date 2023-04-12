package com.github.bestcommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class CategoryStore extends Category{
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products =new HashSet<>();
    @OneToMany(mappedBy = "categoryStore", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Store> stores = new HashSet<>();

    public CategoryStore() {
    }

    public CategoryStore(UUID id, String name, String description, CategoryType type) {
        super(id, name, description, type);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set<Store> getStores() {
        return stores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryStore that = (CategoryStore) o;
        return Objects.equals(products, that.products) && Objects.equals(stores, that.stores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, stores);
    }
}
