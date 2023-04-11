package com.github.bestcommerce.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    @ManyToOne
    @JoinColumn(name = "categoryStore_id")
    private Category categoryStore;

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
}
