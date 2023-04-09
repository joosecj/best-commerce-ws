package com.github.bestcommerce.dtos.v1;

import com.github.bestcommerce.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class CategoryDTO {
    private UUID id;
    @NotBlank(message = "required field")
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank(message = "required field")
    @Size(min = 3, max = 20)
    private String description;
    @NotBlank(message = "required field")
    private String type;

    public CategoryDTO() {
    }

    public CategoryDTO(UUID id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }
    public CategoryDTO(Category categoryEntity) {
        id = categoryEntity.getId();
        name = categoryEntity.getName();
        description = categoryEntity.getDescription();
        type = String.valueOf(categoryEntity.getType());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
