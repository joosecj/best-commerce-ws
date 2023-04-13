package com.github.bestcommerce.dtos.v1;

import com.github.bestcommerce.entities.User;

import java.util.UUID;

public class UserMinDTO {
    private UUID id;
    private String name;

    public UserMinDTO() {
    }

    public UserMinDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserMinDTO(User userEntity) {
        id = userEntity.getId();
        name = userEntity.getName();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
