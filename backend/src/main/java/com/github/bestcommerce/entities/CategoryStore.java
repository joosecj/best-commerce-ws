package com.github.bestcommerce.entities;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class CategoryStore extends Category{
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;
    @OneToMany(mappedBy = "categoryStore")
    private Set<Store> stores;

    public CategoryStore() {
    }

    public CategoryStore(UUID id, String name, String description, CategoryType type, Set<Product> products, Set<Store> stores) {
        super(id, name, description, type);
        this.products = products;
        this.stores = stores;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set<Store> getStores() {
        return stores;
    }
}
