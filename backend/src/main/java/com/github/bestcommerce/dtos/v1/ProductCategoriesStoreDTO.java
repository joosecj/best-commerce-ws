package com.github.bestcommerce.dtos.v1;

import com.github.bestcommerce.entities.Product;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCategoriesStoreDTO {
    private UUID id;
    @NotBlank(message = "required field")
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank(message = "required field")
    @Size(min = 3, max = 20)
    private String description;
    @NotNull(message = "required field")
    @Positive(message = "invalid price")
    private Double price;
    @NotBlank(message = "required field")
    private String imgUrl;
    @NotEmpty(message = "Must have at least one category")
    private List<CategoryDTO> categories = new ArrayList<>();
    private StoreDTO store;

    public ProductCategoriesStoreDTO() {
    }

    public ProductCategoriesStoreDTO(UUID id, String name, String description, Double price, String imgUrl,
                                     List<CategoryDTO> categories, StoreDTO store) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.categories = categories;
        this.store = store;
    }

    public ProductCategoriesStoreDTO(Product productEntity) {
        id = productEntity.getId();
        name = productEntity.getName();
        description = productEntity.getDescription();
        price = productEntity.getPrice();
        imgUrl = productEntity.getImgUrl();
        categories = productEntity.getCategories().stream().map(CategoryDTO::new).toList();
        store = new StoreDTO(productEntity.getStore());
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

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public StoreDTO getStore() {
        return store;
    }
}
