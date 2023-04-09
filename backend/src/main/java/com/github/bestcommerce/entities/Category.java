package com.github.bestcommerce.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_category")
public abstract class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true, length =  20)
    private String name;
    @Column(nullable = false, unique = true, length =  20)
    private String description;
    @Column(nullable = false)
    private CategoryType type;

    protected Category() {
    }

    protected Category(UUID id, String name, String discription, CategoryType type) {
        this.id = id;
        this.name = name;
        this.description = discription;
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
