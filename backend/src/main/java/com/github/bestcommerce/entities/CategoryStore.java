package com.github.bestcommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_category_store")
public class CategoryStore extends Category {
    @OneToMany(mappedBy = "categoryStore", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Store> stores = new HashSet<>();

    public CategoryStore() {
    }

    public CategoryStore(UUID id, String name, String description, CategoryType type) {
        super(id, name, description, type);
    }

    public Set<Store> getStores() {
        return stores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryStore that = (CategoryStore) o;
        return Objects.equals(stores, that.stores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stores);
    }
}
