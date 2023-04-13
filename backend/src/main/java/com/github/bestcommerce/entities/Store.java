package com.github.bestcommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryStore_id")
    @JsonBackReference
    private Category categoryStore;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Product> products = new HashSet<>();

    public Store() {
    }

    public Store(UUID id, String name, Category categoryStore) {
        this.id = id;
        this.name = name;
        this.categoryStore = categoryStore;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategoryStore() {
        return categoryStore;
    }

    public void setCategoryStore(Category categoryStore) {
        this.categoryStore = categoryStore;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
