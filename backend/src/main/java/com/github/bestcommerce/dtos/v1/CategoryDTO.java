package com.github.bestcommerce.dtos.v1;

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

    public CategoryDTO() {
    }

    public CategoryDTO(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
